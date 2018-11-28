package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.CompanyBean;
import gcp.springmvc.bean.PubParamBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

public class CompanyManager {
	
	private HisWs mHisWs=new HisWs();
	
    public List<CompanyBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	String whereTj = "1=1";
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100157");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	if(!pubParamBean.getA01().equals("")){
    		whereTj += " and DWMC like '%"+pubParamBean.getA01()+"%'";
    	}
    	if(!pubParamBean.getA02().equals("")){
    		whereTj += " and DWBM='"+pubParamBean.getA02()+"'";
    	}
    	if(!pubParamBean.getA03().equals("") && !pubParamBean.getA03().equals("all")){
    		whereTj += " and ZT="+pubParamBean.getA03();
    	}
    	hmap.put("wheretj", whereTj);
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<CompanyBean> listComp = new ArrayList<CompanyBean>();
    	cxOutBean = mHisWs.funSelect(cxInBean);
    	if(cxOutBean.isState()){
    		List<Map<String, String>> list = cxOutBean.getRow01();
    		if(list==null){
    			return null;
    		}
    		for(int i=0; i<list.size(); i++){
    			listComp.add( rowToBean(list.get(i)) );
    		}
    		return listComp;
    	}else{
    		return null;
    	}
    }
    
    public CompanyBean rowToBean(Map<String, String> map){
    	CompanyBean cb = new CompanyBean();
		cb.setDWBM(map.get("DWBM"));
		cb.setDWMC(map.get("DWMC"));
		cb.setPYM(map.get("PYM"));
		cb.setWBM(map.get("WBM"));
		cb.setZT(map.get("ZT"));
		return cb;
    }

    public SaveOutputBean add(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101900");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//DWBM,DWMC,PYM,WBM,ZT
    	vmap.put("DWMC", pubParamBean.getA01());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }

    public SaveOutputBean update(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101901");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//DWBM,DWMC,PYM,WBM,ZT
    	vmap.put("DWMC", pubParamBean.getA01());
    	vmap.put("DWBM", pubParamBean.getA02());
    	vmap.put("ZT", pubParamBean.getA03());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
    
    public SaveOutputBean delete(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101902");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	saveInBean.setArg1(pubParamBean.getA01());
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }  
    
}
