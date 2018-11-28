package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.GcpFymxBean;
import gcp.springmvc.bean.PubParamBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

public class GcpFymxManager {
	/*员工*/
	private HisWs mHisWs=new HisWs();
	
    public List<GcpFymxBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100316");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("SSJ", pubParamBean.getA01());
    	hmap.put("ESJ", pubParamBean.getA02());
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<GcpFymxBean> listBean = new ArrayList<GcpFymxBean>();
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
    
    public GcpFymxBean rowToBean(Map<String, String> map){
    	GcpFymxBean bean = new GcpFymxBean();
    	bean.setXM(map.get("XM"));
    	bean.setXMMC(map.get("XMMC"));
    	bean.setYZJE(map.get("YZJE"));
    	bean.setYZNR(map.get("YZNR"));
    	bean.setYZZXSJ(map.get("YZZXSJ"));
    	bean.setZXKSDM(map.get("ZXKSDM"));
    	bean.setZXKSMC(map.get("ZXKSMC"));
		return bean;
    }
}
