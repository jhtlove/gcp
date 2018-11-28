package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.BedInfoBean;
import gcp.springmvc.bean.PubParamBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

public class BedInfoManager{
	
	private HisWs mHisWs=new HisWs();
	
    public List<BedInfoBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100181");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("YI", "1");
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<BedInfoBean> listBean = new ArrayList<BedInfoBean>();
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
    
    public BedInfoBean rowToBean(Map<String, String> map){
    	BedInfoBean bean = new BedInfoBean();
    	bean.setKSID(map.get("KSID"));
    	bean.setKSMC(map.get("KSMC"));
    	bean.setCWZS(map.get("CWZS"));
    	bean.setDCYBRS(map.get("DCYBRS"));
    	bean.setDRYBRS(map.get("DRYBRS"));
    	bean.setJCWS(map.get("JCWS"));
    	bean.setXRYBRS(map.get("XRYBRS"));
    	bean.setYYCWS(map.get("YYCWS"));
    	bean.setZCWS(map.get("ZCWS"));
    	bean.setXZRBRS(map.get("XZRBRS"));
    	bean.setBLRQ(map.get("BLRQ"));
		return bean;
    }

}
