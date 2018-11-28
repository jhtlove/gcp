package hissock.hjxt.contr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.bean.ResProjectBean;
import gcp.springmvc.manager.ResProjectManager;
import hissock.hjxt.manager.TjCallerManager;
import ihis.busiws.CxInputBean;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

/**
 * 考虑是通过web springMVC方式提供调用业务，
 * 还是通过WebServices Json方式来提供调用服务
 */
@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/tjcaller")
@Controller
public class TjCallerController {
	private TjCallerManager mTjCallerManager = new TjCallerManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("tjcaller/query");
		String usernbid,password,username;
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		try{
	    	System.out.println("{Session}: username:"+map.get("username").toString()+",usernbid:"+map.get("usernbid")+", password:"+map.get("password"));
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	username = map.get("username").toString();
	    	if(usernbid==null || usernbid.length()<=0){
	    		rtnMap = null;
	    		return null;
	    	}
	    	CxInputBean cxInBean = new CxInputBean();
	    	cxInBean.setUserid(usernbid);
	    	cxInBean.setPassword(password);
	    	pubParamBean.setA30(username);
	    	List<ResProjectBean> listBean = mTjCallerManager.query(cxInBean, pubParamBean);
	    	cxInBean = null;
	    	if(listBean==null){
	    		rtnMap.put("rows", "0");
	    	}else{
	    		rtnMap.put("rows", listBean);
	    		listBean = null;
	    	}
	    	return rtnMap;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(">>>tjcaller/query异常："+e.getMessage());
			return rtnMap;
		}finally{
			usernbid = null;
			password = null;
			rtnMap = null;
			pubParamBean = null;
			map = null;
		}
    }
	
	/** 修改---呼叫时修改排队记录状态 */
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean update(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("tjcaller/update");
		String usernbid,password;
		SaveOutputBean rtnSaveOut;
		//update KYXM_XMML set KYDW=?,XMLX=?,XMMC=?,PYM=?,WBM=?,YXRQ=?,ZBRQ=? where XMBM=?
		try{
			usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	SaveInputBean saveInBean = new SaveInputBean();
	    	saveInBean.setUserid(usernbid);
	    	saveInBean.setPassword(password);
	    	String xmbm = pubParamBean.getA01();
	    	if(xmbm!=null && xmbm.length()>0){
	    		rtnSaveOut = mTjCallerManager.update(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>tjcaller/update参数错误：“项目编码”参数为空！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("tjcaller/update参数错误：“项目编码”参数为空！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>tjcaller/update异常："+e.getMessage());
			rtnSaveOut = new SaveOutputBean();
			rtnSaveOut.setState(false);
			rtnSaveOut.setErrorMsg("tjcaller/update异常："+e.getMessage());
			return rtnSaveOut;
		}
    	
    }
	

}
