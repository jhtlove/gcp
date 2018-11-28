package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.DoctorAdviceBean;
import gcp.springmvc.bean.PubParamBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

public class DoctorAdviceManager{
	
	private HisWs mHisWs=new HisWs();
	
    public List<DoctorAdviceBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100175");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("BZ", "1");
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<DoctorAdviceBean> listBean = new ArrayList<DoctorAdviceBean>();
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
    
    public DoctorAdviceBean rowToBean(Map<String, String> map){
    	DoctorAdviceBean bean = new DoctorAdviceBean();
    	bean.setDM(map.get("DM"));
    	bean.setYZLX(map.get("YZLX"));
    	bean.setDMMC(map.get("DMMC"));
    	bean.setPYM(map.get("PYM"));
    	bean.setWBM(map.get("WBM"));
    	bean.setYZFL(map.get("YZFL"));
    	bean.setMRZXKS(map.get("MRZXKS"));
    	bean.setMRZXKSMC(map.get("MRZXKSMC"));
    	bean.setYZJE(map.get("YZJE"));
		return bean;
    }

}
