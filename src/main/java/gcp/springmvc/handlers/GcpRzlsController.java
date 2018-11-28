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

import gcp.springmvc.bean.GcpRzlsBean;
import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.manager.GcpRzlsManager;
import ihis.busiws.CxInputBean;

@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/gcprzls")
@Controller
public class GcpRzlsController {
	/**
	 *　可以通过spring自动注入
	**/
	private GcpRzlsManager mGcpRzlsManager = new GcpRzlsManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("gcprzls/query");
		String usernbid,password;
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		try{
	    	System.out.println("{Session}: username:"+map.get("username").toString()+",usernbid:"+map.get("usernbid")+", password:"+map.get("password"));
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	CxInputBean cxInBean = new CxInputBean();
	    	cxInBean.setUserid(usernbid);
	    	cxInBean.setPassword(password);
	    	List<GcpRzlsBean> listBean = mGcpRzlsManager.query(cxInBean, pubParamBean);
	    	if(listBean==null){
	    		rtnMap.put("rows", "[]");
	    	}else{
	    		rtnMap.put("rows", listBean);
	    	}
	    	return rtnMap;
		}catch(Error e){
			System.out.println(">>>workers/query异常："+e.getMessage());
			rtnMap.put("rows", "0");
			return rtnMap;
		}
    }
}
