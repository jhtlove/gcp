package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.BaseTableBean;
import gcp.springmvc.bean.PubParamBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

public class BaseTableManager{
	
	private HisWs mHisWs=new HisWs();
	
    public List<BaseTableBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100173");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("LXID", pubParamBean.getA01());
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<BaseTableBean> listBean = new ArrayList<BaseTableBean>();
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
    
    public BaseTableBean rowToBean(Map<String, String> map){
    	BaseTableBean bean = new BaseTableBean();
    	bean.setID(map.get("ID"));
    	bean.setLXID(map.get("LXID"));
    	bean.setXH(map.get("XH"));
    	bean.setDM(map.get("DM"));
    	bean.setUNAME(map.get("UNAME"));
    	bean.setPYM(map.get("PYM"));
    	bean.setWBM(map.get("WBM"));
    	bean.setKYBZ(map.get("KYBZ"));
    	bean.setZDY1(map.get("ZDY1"));
    	bean.setZDY2(map.get("ZDY2"));
    	bean.setZDY3(map.get("ZDY3"));
    	bean.setZDY4(map.get("ZDY4"));
    	bean.setZDY5(map.get("ZDY5"));
    	bean.setZDY6(map.get("ZDY6"));
    	bean.setZDY7(map.get("ZDY7"));
    	bean.setZDY8(map.get("ZDY8"));
    	bean.setZDY9(map.get("ZDY9"));
    	bean.setXH(map.get("XH"));
    	bean.setDM(map.get("DM"));
    	bean.setUNAME(map.get("UNAME"));
		return bean;
    }

}
