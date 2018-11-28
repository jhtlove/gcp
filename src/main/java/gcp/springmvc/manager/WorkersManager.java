package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.bean.WorkersBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

public class WorkersManager {
	/*员工*/
	private HisWs mHisWs=new HisWs();
	
    public List<WorkersBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100166");
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
    	List<WorkersBean> listBean = new ArrayList<WorkersBean>();
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
    
    public WorkersBean rowToBean(Map<String, String> map){
    	WorkersBean bean = new WorkersBean();
    	bean.setBMNBBH(map.get("BMNBBH"));
    	bean.setBMMC(map.get("BMMC"));
    	bean.setCZYDM(map.get("CZYDM"));
    	bean.setCZYXM(map.get("CZYXM"));
    	bean.setPYM(map.get("PYM"));
    	bean.setWBM(map.get("WBM"));
    	bean.setLXDH(map.get("LXDH"));
		return bean;
    }
}
