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
import gcp.springmvc.bean.StageAdviceBean;
import gcp.springmvc.manager.StageAdviceManager;
import ihis.busiws.CxInputBean;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

@SessionAttributes(value={"username","usernbid","password"})
@RequestMapping(value="/stageadvice")
@Controller
public class StageAdviceController{
	/**
	 *　可以通过spring自动注入
	**/
	private StageAdviceManager mStageAdviceManager = new StageAdviceManager();
	
	@RequestMapping(value="/query.do", method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> query(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("stageadvice/query");
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
	    	List<StageAdviceBean> listBean = mStageAdviceManager.query(cxInBean, pubParamBean);
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
			System.out.println(">>>stageadvice/query异常："+e.getMessage());
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
		System.out.println("stageadvice/add");
		String usernbid,password;
		SaveOutputBean rtnSaveOut;
		try{
	    	usernbid = map.get("usernbid").toString();
	    	password = map.get("password").toString();
	    	SaveInputBean saveInBean = new SaveInputBean();
	    	saveInBean.setUserid(usernbid);
	    	saveInBean.setPassword(password);
	    	pubParamBean.setA01("") ;
	    	//(ID,GYID,XH,YZLB,YZDM,YZMC,ZXKS,BZXM,KYBZ,JL,YF,YFSM,PD,TS,ZSL,JZFS)
	    	/*
	    	if(pubParamBean.getA11()==null || pubParamBean.getA11().length()<=0){
	    		pubParamBean.setA11("0");//JL
	    	}
	    	if(pubParamBean.getA15()==null || pubParamBean.getA15().length()<=0){
	    		pubParamBean.setA15("0");//TS
	    	}
	    	if(pubParamBean.getA16()==null || pubParamBean.getA16().length()<=0){
	    		pubParamBean.setA16("0");//ZSL
	    	}*/
	    	String A02 = pubParamBean.getA02();
	    	if(A02!=null && A02.length()>0 ){
	    		rtnSaveOut = mStageAdviceManager.add(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>stageadvice/add参数错误：A02参数不正确！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("stageadvice/add参数错误：A02参数不正确！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>stageadvice/add异常："+e.getMessage());
			SaveOutputBean rtn = new SaveOutputBean();
			rtn.setState(false);
			rtn.setErrorMsg(e.getMessage());
			return rtn;
		}
    }
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
    public @ResponseBody SaveOutputBean update(@ModelAttribute("pubParamBean")PubParamBean pubParamBean, ModelMap map){
		System.out.println("stageadvice/update");
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
	    		rtnSaveOut = mStageAdviceManager.update(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>stageadvice/update参数错误：A01，A02参数不正确！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("stageadvice/update参数错误：A01，A02参数不正确！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>stageadvice/update异常："+e.getMessage());
			rtnSaveOut = new SaveOutputBean();
			rtnSaveOut.setState(false);
			rtnSaveOut.setErrorMsg("stageadvice/update异常："+e.getMessage());
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
	    		rtnSaveOut = mStageAdviceManager.delete(saveInBean, pubParamBean);
	    	}else{
	    		System.out.println(">>>stageadvice/delete参数错误：A01，A02参数不正确！");
	    		rtnSaveOut = new SaveOutputBean();
				rtnSaveOut.setState(false);
				rtnSaveOut.setErrorMsg("stageadvice/delete参数错误：A01，A02参数不正确！");
	    	}
	    	return rtnSaveOut;
		}catch(Error e){
			System.out.println(">>>stageadvice/delete异常："+e.getMessage());
			rtnSaveOut = new SaveOutputBean();
			rtnSaveOut.setState(false);
			rtnSaveOut.setErrorMsg("stageadvice/delete异常："+e.getMessage());
			return rtnSaveOut;
		}
    }
}
