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

import gcp.springmvc.bean.BaseTableBean;
import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.manager.BaseTableManager;
import ihis.busiws.CxInputBean;


@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/basetable")
@Controller
public class BaseTableController{
	/**
	 *　可以通过spring自动注入
	**/
	private BaseTableManager mBaseTableManager = new BaseTableManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("basetable/query");
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
	    	List<BaseTableBean> listBean = mBaseTableManager.query(cxInBean, pubParamBean);
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
			System.out.println(">>>basetable/query异常："+e.getMessage());
			return rtnMap;
		}finally{
			usernbid = null;
			password = null;
			rtnMap = null;
			pubParamBean = null;
			map = null;
		}
    }

}
