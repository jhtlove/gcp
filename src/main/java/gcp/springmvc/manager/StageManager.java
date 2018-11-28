package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.bean.StageBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

public class StageManager{
	
	private HisWs mHisWs=new HisWs();
	
    public List<StageBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100172");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("XMBM", pubParamBean.getA01());
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<StageBean> listBean = new ArrayList<StageBean>();
    	cxOutBean = mHisWs.funSelect(cxInBean);
    	if(cxOutBean.isState()){
    		System.out.println("query success");
    		List<Map<String, String>> list = cxOutBean.getRow01();
    		if(list==null){
    			return null;
    		}
    		for(int i=0; i<list.size(); i++){
    			listBean.add( rowToBean(list.get(i)) );
    		}
    		return listBean;
    	}else{
    		return null;
    	}
    }
    
    public StageBean rowToBean(Map<String, String> map){
    	StageBean bean = new StageBean();
    	bean.setID(map.get("ID"));
    	bean.setXMBM(map.get("XMBM"));
    	bean.setXH(map.get("XH"));
    	bean.setJDLX(map.get("JDLX"));
    	bean.setJDLXMC(map.get("JDLXMC"));
    	bean.setJDMC(map.get("JDMC"));
		return bean;
    }

    public SaveOutputBean add(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101915");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//insert into KYXM_XMML_SYJD(ID,XMBM,XH,JDLX,JDMC) values(?,?,?,?,?)
    	vmap.put("XMBM", pubParamBean.getA01());
    	vmap.put("JDLX", pubParamBean.getA02());
    	vmap.put("JDMC", pubParamBean.getA03());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }

    public SaveOutputBean update(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101916");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//update KYXM_XMML_SYJD set JDLX=?,JDMC=? where XMBM=? and ID=?
    	vmap.put("ID", pubParamBean.getA01());
    	vmap.put("XMBM", pubParamBean.getA02());
    	vmap.put("JDLX", pubParamBean.getA03());
    	vmap.put("JDMC", pubParamBean.getA04());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	System.out.println(">>>>>>>>>>>"+saveInBean.funGetJson());
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
    
    public SaveOutputBean delete(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101917");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	saveInBean.setArg1(pubParamBean.getA01());
    	saveInBean.setArg2(pubParamBean.getA02());
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
    
}
