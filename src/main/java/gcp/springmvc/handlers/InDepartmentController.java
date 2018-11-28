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

import gcp.springmvc.bean.InDepartmentBean;
import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.manager.InDepartmentManager;
import ihis.busiws.CxInputBean;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/indepartment")
@Controller
public class InDepartmentController {
	/**
	 *　可以通过spring自动注入
	**/
	private InDepartmentManager mInDepartmentManager = new InDepartmentManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("indepartment/query");
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
	    	List<InDepartmentBean> listBean = mInDepartmentManager.query(cxInBean, pubParamBean);
	    	if(listBean==null){
	    		rtnMap.put("rows", "0");
	    	}else{
	    		rtnMap.put("rows", listBean);
	    	}
	    	return rtnMap;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(">>>indepartment/query异常："+e.getMessage());
			return null;
		}finally{
			usernbid = null;
    		password = null;
    		rtnMap = null;
		}
    }
	
	@RequestMapping(value="/add.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean add(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("indepartment/add");
		String usernbid,password;
		SaveOutputBean rtnSaveOut;
		try{
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	SaveInputBean saveInBean = new SaveInputBean();
	    	saveInBean.setUserid(usernbid);
	    	saveInBean.setPassword(password);
	    	String A01 = pubParamBean.getA01();
	    	if(A01!=null && A01.length()>0){
	    		rtnSaveOut = mInDepartmentManager.add(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>indepartment/add参数错误：“项目编码”参数为空！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("indepartment/add参数错误：“项目编码”参数为空！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>indepartment/add异常："+e.getMessage());
			rtnSaveOut = new SaveOutputBean();
			rtnSaveOut.setState(false);
			rtnSaveOut.setErrorMsg(e.getMessage());
			return rtnSaveOut;
		}
    }
}
