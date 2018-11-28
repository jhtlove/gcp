package gcp.springmvc.handlers;

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
import gcp.springmvc.bean.ResProjectDetailBean;
import gcp.springmvc.manager.ResProjectDetailManager;
import ihis.busiws.CxInputBean;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/projectdetail")
@Controller
public class ResProjectDetailController {
	/**
	 *　可以通过spring自动注入
	**/
	private ResProjectDetailManager mResProjectDetailManager = new ResProjectDetailManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("projectdetail/query");
		String usernbid,password;
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		try{
	    	System.out.println("{Session}: username:"+map.get("username").toString()+",usernbid:"+map.get("usernbid")+", password:"+map.get("password"));
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	if(usernbid==null || usernbid.length()<=0){
	    		return null;
	    	}
	    	String A01 = pubParamBean.getA01();
	    	if(A01==null && A01.length()<=0){
	    		return null;
	    	}
	    	CxInputBean cxInBean = new CxInputBean();
	    	cxInBean.setUserid(usernbid);
	    	cxInBean.setPassword(password);
	    	List<ResProjectDetailBean> listBean = mResProjectDetailManager.query(cxInBean, pubParamBean);
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
			System.out.println(">>>projectdetail/query异常："+e.getMessage());
			return rtnMap;
		}finally{
			usernbid = null;
			password = null;
			rtnMap = null;
			pubParamBean = null;
			map = null;
		}
    }
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean update(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("projectdetail/update");
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
	    		rtnSaveOut = mResProjectDetailManager.update(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>projectdetail/update参数错误：“项目编码”参数为空！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("projectdetail/update参数错误：“项目编码”参数为空！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>projectdetail/update异常："+e.getMessage());
			rtnSaveOut = new SaveOutputBean();
			rtnSaveOut.setState(false);
			rtnSaveOut.setErrorMsg("projectdetail/update异常："+e.getMessage());
			return rtnSaveOut;
		}
    	
    }
}
