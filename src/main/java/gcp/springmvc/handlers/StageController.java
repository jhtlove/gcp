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
import gcp.springmvc.bean.StageBean;
import gcp.springmvc.manager.StageManager;
import ihis.busiws.CxInputBean;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/stage")
@Controller
public class StageController{
	/**
	 *　可以通过spring自动注入
	**/
	private StageManager mStageManager = new StageManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("stage/query");
		String usernbid,password;
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		try{
	    	System.out.println("{Session}: username:"+map.get("username").toString()+",usernbid:"+map.get("usernbid")+", password:"+map.get("password"));
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	if(usernbid==null || usernbid.length()<=0){
	    		rtnMap = null;
	    		return null;
	    	}
	    	CxInputBean cxInBean = new CxInputBean();
	    	cxInBean.setUserid(usernbid);
	    	cxInBean.setPassword(password);
	    	List<StageBean> listBean = mStageManager.query(cxInBean, pubParamBean);
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
			System.out.println(">>>stage/query异常："+e.getMessage());
			return rtnMap;
		}finally{
			usernbid = null;
			password = null;
			rtnMap = null;
			pubParamBean = null;
			map = null;
		}
    }
	
	@RequestMapping(value="/add.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean add(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("stage/add");
		String usernbid,password;
		SaveOutputBean rtnSaveOut;
		try{
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	SaveInputBean saveInBean = new SaveInputBean();
	    	saveInBean.setUserid(usernbid);
	    	saveInBean.setPassword(password);
	    	String A01 = pubParamBean.getA01();
	    	String A02 = pubParamBean.getA02();
	    	if(A01!=null && A01.length()>0 && A02!=null && A02.length()>0 ){
	    		rtnSaveOut = mStageManager.add(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>stage/add参数错误：A01，A02参数不正确！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("stage/add参数错误：A01，A02参数不正确！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>stage/add异常："+e.getMessage());
			SaveOutputBean rtn = new SaveOutputBean();
			rtn.setState(false);
			rtn.setErrorMsg(e.getMessage());
			return rtn;
		}
    }
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean update(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("stage/update");
		String usernbid,password;
		SaveOutputBean rtnSaveOut;
		//update KYXM_XMML set KYDW=?,XMLX=?,XMMC=?,PYM=?,WBM=?,YXRQ=?,ZBRQ=? where XMBM=?
		try{
			usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	SaveInputBean saveInBean = new SaveInputBean();
	    	saveInBean.setUserid(usernbid);
	    	saveInBean.setPassword(password);
	    	String A01 = pubParamBean.getA01();
	    	String A02 = pubParamBean.getA02();
	    	if(A01!=null && A01.length()>0 && A02!=null && A02.length()>0 ){
	    		rtnSaveOut = mStageManager.update(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>stage/update参数错误：A01，A02参数不正确！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("stage/update参数错误：A01，A02参数不正确！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>stage/update异常："+e.getMessage());
			rtnSaveOut = new SaveOutputBean();
			rtnSaveOut.setState(false);
			rtnSaveOut.setErrorMsg("stage/update异常："+e.getMessage());
			return rtnSaveOut;
		}
    	
    }
	
	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean delete(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("delete");
		String usernbid,password;
		SaveOutputBean rtnSaveOut;
		try{
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	SaveInputBean saveInBean = new SaveInputBean();
	    	saveInBean.setUserid(usernbid);
	    	saveInBean.setPassword(password);
	    	String A01 = pubParamBean.getA01();
	    	String A02 = pubParamBean.getA02();
	    	if(A01!=null && A01.length()>0 && A02!=null && A02.length()>0 ){
	    		rtnSaveOut = mStageManager.delete(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>stage/delete参数错误：A01，A02参数不正确！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("stage/delete参数错误：A01，A02参数不正确！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>stage/delete异常："+e.getMessage());
			rtnSaveOut = new SaveOutputBean();
			rtnSaveOut.setState(false);
			rtnSaveOut.setErrorMsg("stage/delete异常："+e.getMessage());
			return rtnSaveOut;
		}
    }
}
