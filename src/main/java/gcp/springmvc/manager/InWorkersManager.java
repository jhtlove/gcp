package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.InWorkersBean;
import gcp.springmvc.bean.PubParamBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

public class InWorkersManager {
	
	private HisWs mHisWs=new HisWs();
	
    public List<InWorkersBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100168");
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
    	List<InWorkersBean> listBean = new ArrayList<InWorkersBean>();
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
    
    public InWorkersBean rowToBean(Map<String, String> map){
    	InWorkersBean bean = new InWorkersBean();
    	bean.setID(map.get("ID"));
    	bean.setXMBM(map.get("XMBM"));
    	bean.setBMNBBH(map.get("BMNBBH"));
    	bean.setBMMC(map.get("BMMC"));
    	bean.setCZYDM(map.get("CZYDM"));
    	bean.setCZYXM(map.get("CZYXM"));
    	bean.setPYM(map.get("PYM"));
    	bean.setWBM(map.get("WBM"));
    	bean.setLXDH(map.get("LXDH"));
		return bean;
    }

    public SaveOutputBean add(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101912");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap;
    	//ID,XMBM,CZYDM
    	String yggh = pubParamBean.getA02();
    	if(yggh!=null && yggh.length()>0){
    		String[] arrayYggh = yggh.split(",");//参与人员工号(00100,00101,00102)
    		for(int i=0; i<arrayYggh.length; i++){
    			vmap = new HashMap<String, String>(); 
    			vmap.put("CZYDM", arrayYggh[i]);
    			xml1.add(vmap);
    		}
    	}//else 删除全部
    	saveInBean.setArg1(pubParamBean.getA01());//项目编码
    	saveInBean.setXml1(xml1);
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
}
