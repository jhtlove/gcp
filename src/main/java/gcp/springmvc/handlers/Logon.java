package gcp.springmvc.handlers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import ihis.bean.UserBean;
import ihis.busiws.HisWs;
import ihis.busiws.SaveOutputBean;

@SessionAttributes(value={"username","usernbid","password","islogin"})
@Controller
public class Logon {   
    @RequestMapping(value="logon.do", method=RequestMethod.POST)
    public String logon(@ModelAttribute("userBean") UserBean userBean, ModelMap map){
    	String username,spassword;
    	SaveOutputBean outbean;
    	try{
    		username=userBean.getUsername();
        	spassword=userBean.getPassword();
        	map.put("username", username);
        	map.put("password", userBean.getPassword());
        	HisWs hisws=new HisWs();
        	outbean=hisws.funLogin(username, spassword);
        	if(outbean.isState()){
        		System.out.println("login success!!!");
        		map.put("usernbid", outbean.getA01());
        		map.put("yourname", outbean.getA02());
        		map.put("islogin", "true");
        		return "main";
        	}else{
        		map.put("username", "");
        		map.put("usernbid", "");
        		map.put("password", "");
        		map.put("islogin", "false");
        		return "forward:/index.jsp";
        	}
    	}catch(Error e){
    		System.out.println(">>>logon调用异常："+e.getMessage());
			return "forward:/index.jsp";
    	}

    }    
    @RequestMapping(value="logout.do", method=RequestMethod.GET)
    public ModelAndView logout(@ModelAttribute("UserBean") UserBean userBean, SessionStatus sessionStatus){     
        //@ModelAttribute("User")相当于将session中名为"User"的对象注入user对象中  
        //sessionStatus中的setComplete方法可以将session中的内容全部清空  
        sessionStatus.setComplete();
        return new ModelAndView("forward:/index.jsp");
    }  
    
    @RequestMapping(value="islogin.do", method=RequestMethod.GET)
    public @ResponseBody String islogin(ModelMap map){
    	try{
    		String islogin = map.get("islogin").toString();
    		return islogin;
    	}catch(Exception e){
    		e.printStackTrace();
    		return "false";
    	}
    }
    
    @RequestMapping(value="go.do", method=RequestMethod.GET)
    public String go(@RequestParam("p") String p,ModelMap map){
    	try{
    		if(map.get("islogin").equals("true")){ 
    			if(p!=null && p.length()>0){
    				return p;
    			}else{
    				return "redirect:/index.jsp";
    			}
            }else{
            	return "redirect:/index.jsp";
            }
    	}catch(Exception e){
    		e.printStackTrace();
    		return "redirect:/index.jsp";
    	}
    } 
}
