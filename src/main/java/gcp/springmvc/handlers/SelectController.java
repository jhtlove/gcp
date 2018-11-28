package gcp.springmvc.handlers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import gcp.springmvc.bean.CompanyBean;
import gcp.springmvc.manager.CompanyManager;
import ihis.bean.UserBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

@SessionAttributes(value={"username","usernbid","password"})
@Controller
public class SelectController {
	@RequestMapping(value="selectks111.do", method=RequestMethod.POST)
    public @ResponseBody String selectKs(@ModelAttribute("userBean") UserBean userBean, ModelMap map){
    	String usernbid,spassword;
    	CxOutputBean cxOutBean = new CxOutputBean();
    	CxInputBean cxInBean = new CxInputBean();
    	
    	System.out.println(map.get("username").toString()+map.get("usernbid")+map.get("password"));
    	usernbid = map.get("usernbid").toString();
    	spassword = map.get("password").toString();
    	cxInBean.setBusiid("10100114");
    	cxInBean.setUserid(usernbid);
    	cxInBean.setPassword(spassword);
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("BZ", "1");
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("selectKs in:"+cxInBean.funGetJson());

    	HisWs hisws=new HisWs();
    	cxOutBean=hisws.funSelect(cxInBean);
    	if(cxOutBean.isState()){
    		System.out.println("login success!");
    		return ""+cxOutBean.getRow01();
    	}else{
    		return "";
    	}
    }
	
	@RequestMapping(value="selectks3.do", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> selectCompanys(){
    	String usernbid,password;
//    	System.out.println("Session: username:"+map.get("username").toString()+",usernbid:"+map.get("usernbid")+", password:"+map.get("password"));
    	usernbid = "3626";//map.get("usernbid").toString();
    	password = "111";//map.get("password").toString();
    	CxInputBean cxInBean = new CxInputBean();
    	Map<String,Object> vcmap = new HashMap<String,Object>();
    	Map<String,Object> mmm1 = new HashMap<String,Object>();
    	mmm1.put("dwbm","0001");
    	mmm1.put("dwmc","AAAA");
    	mmm1.put("kybz","1");
    	List<Map<String,Object>> vliss = new ArrayList<Map<String,Object>>();
    	Map<String,Object> mmm2 = new HashMap<String,Object>();
    	mmm2.put("dwbm","0002");
    	mmm2.put("dwmc","BBBB");
    	mmm2.put("kybz","1");
    	vliss.add(mmm1);
    	vliss.add(mmm2);
    	vcmap.put("rows",vliss);
    	
    	cxInBean.setUserid(usernbid);
    	cxInBean.setPassword(password);
    	CompanyManager mCompanyManager = new CompanyManager();
    	//CompanyBean compayBean = (CompanyBean) mCompanyManager.queryOne(cxInBean);
    	
    	return vcmap;//compayBean.getRows();
    } 
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> selectCompany(){
	       StringBuffer s = new StringBuffer("Hello"); 
	       Map<String,Object> rtnMap = new HashMap<String,Object>();
	       List<Map<String,String>> listBean = new ArrayList<Map<String, String>>();
	       Map<String, String> map = new HashMap<String, String>();
	       map.put("key", "value");
	       listBean.add(map);
	       rtnMap.put("rows", listBean);
	        try {  
	        	return rtnMap; 
	        } catch (Exception e) {  
	            return null;  
	        }finally{ 
	        	rtnMap = null;
	            
	            System.out.println("execute finally2");  
	        }  
    }
	
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
    public @ResponseBody String test1(){
	       String sj = "{\"state\":\"true\",\"rows\":[{\"key\":1,\"value\":1},{\"key\":2,\"value\":2}]}";
	       sj = "{\"state\":\"true\",\"rows\":[null]}";
	       JsonObject jsonObj1 = new JsonObject();
	       JsonParser parser = new JsonParser();
	       try{
	       JsonObject jsonObj = new JsonParser().parse(sj).getAsJsonObject();
	       
	       Gson gson = new Gson();
	       //jsonObj = gson.fromJson(sj, JsonObject.class);
	       if(jsonObj.has("rows")){
	    	   if(jsonObj.has("key")){
	    		   System.out.println("you key");
	    	   }else{
	    		   System.out.println("mei you key");  
	    	   }
	    	   System.out.println(jsonObj.getAsJsonArray("rows").toString());
	    	   JsonArray jsonArray = jsonObj.getAsJsonArray("rows");
	    	   if(jsonArray==null){
	    		   System.out.println("jsonArray==null");
	    	   }else{
	    		   System.out.println(jsonArray.toString());
	    	   }
	    	   String s = jsonObj.get("state").getAsString();
	    	   System.out.println("state:"+s);
//	    	   JsonElement je = jsonArray.get(0);
//	    	   je.getAsJsonObject();
	    	   
//	    	   
//	    	   for(int i=0; i<jsonArray.size(); i++){
//	    		   //System.out.println(jsonArray.get(i).toString());
//	    		   je = jsonArray.get(i);
//	    		   System.out.println(je.getAsString());
//	    	   }

//	    	   Type type = new TypeToken<ArrayList<Map<String,String>>>(){}.getType();
//	    	   ArrayList<Map<String,String>> sList=gson.fromJson(jsonArray.toString(), type);
//	    	   System.out.println("111222:"+sList.toString());
	    	   
	    	   return "true";
	       }else{
	    	   System.out.println("no has  .......");
	    	   return "false";
	       }
	       }catch(Exception e){
	    	   e.printStackTrace();
	    	   return "false";
	       }
    }

}


