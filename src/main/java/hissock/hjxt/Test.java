package hissock.hjxt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.pubfun.Pfun;

public class Test {
	private static HisWs mHisWs=new HisWs();
	public static void main(String[] args) {
		CxInputBean cxInBean = new CxInputBean();
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100179");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("ZYBM", "TJ1001");
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	List<Map<String, String>> rtnList = null;
    	System.out.println("start...");
    	cxOutBean = mHisWs.funSelect(cxInBean);
    	System.out.println("stasdf...");
    	if(cxOutBean.isState()){
    		System.out.println("查询队列数据");
    		rtnList = cxOutBean.getRow01();
    		System.out.println("size:"+rtnList.size());
    		return ;
    	}else{
    		return ;
    	}
	}
}
