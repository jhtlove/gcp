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

import gcp.springmvc.bean.CompanyBean;
import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.manager.CompanyManager;
import ihis.busiws.CxInputBean;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/company")
@Controller
public class CompanyController {
	/**
	 *　可以通过spring自动注入
	**/
	private CompanyManager mCompanyManager = new CompanyManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("query");
		String usernbid,password;
		Map<String,Object> rtnMap = new HashMap<String,Object>();
    	try{
    		System.out.println("{Session}: username:"+map.get("username").toString()+",usernbid:"+map.get("usernbid")+", password:"+map.get("password"));
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	if(usernbid==null || usernbid.length()<=0){
	    		return null;
	    	}
	    	CxInputBean cxInBean = new CxInputBean();
	    	cxInBean.setUserid(usernbid);
	    	cxInBean.setPassword(password);
	    	List<CompanyBean> listBean = mCompanyManager.query(cxInBean, pubParamBean);
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
    		System.out.println(">>>company/query异常："+e.getMessage());
			return null;
    	}finally{
    		usernbid = null;
    		password = null;
    		rtnMap = null;
    	}
    }
	
	@RequestMapping(value="/add.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean add(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("add");
		String usernbid,password;
    	usernbid = map.get("usernbid").toString();
    	password = map.get("password").toString();
    	SaveInputBean saveInBean = new SaveInputBean();
    	saveInBean.setUserid(usernbid);
    	saveInBean.setPassword(password);
    	SaveOutputBean sopb = mCompanyManager.add(saveInBean, pubParamBean);
    	if(sopb!=null && sopb.isState()){
    		return sopb;
    	}
    	return sopb;
    }
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean update(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("update");
		String usernbid,password;
    	usernbid = map.get("usernbid").toString();
    	password = map.get("password").toString();
    	SaveInputBean saveInBean = new SaveInputBean();
    	saveInBean.setUserid(usernbid);
    	saveInBean.setPassword(password);
    	SaveOutputBean sopb = mCompanyManager.update(saveInBean, pubParamBean);
    	if(sopb!=null && sopb.isState()){
    		return sopb;
    	}
    	return sopb;
    }
	
	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean delete(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("delete");
		String usernbid,password;
    	usernbid = map.get("usernbid").toString();
    	password = map.get("password").toString();
    	SaveInputBean saveInBean = new SaveInputBean();
    	saveInBean.setUserid(usernbid);
    	saveInBean.setPassword(password);
    	SaveOutputBean sopb = mCompanyManager.delete(saveInBean, pubParamBean);
    	if(sopb!=null && sopb.isState()){
    		return sopb;
    	}
    	return sopb;
    }
}
