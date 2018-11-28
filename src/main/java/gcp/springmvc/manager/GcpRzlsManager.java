package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.GcpRzlsBean;
import gcp.springmvc.bean.PubParamBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

public class GcpRzlsManager {
	/*员工*/
	private HisWs mHisWs=new HisWs();
	
    public List<GcpRzlsBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100317");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("A", "1");
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<GcpRzlsBean> listBean = new ArrayList<GcpRzlsBean>();
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
    
    public GcpRzlsBean rowToBean(Map<String, String> map){
    	GcpRzlsBean bean = new GcpRzlsBean();
    	bean.setXMBM(map.get("XMBM"));
    	bean.setXMMC(map.get("XMMC"));
    	bean.setSYFQ(map.get("SYFQ"));
    	bean.setCDZY(map.get("CDZY"));
    	bean.setRZLS(map.get("RZLS"));
		return bean;
    }
}
