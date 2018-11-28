package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.GnBean;
import gcp.springmvc.bean.MkBean;
import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.bean.XtBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;

public class MenuManager {
	
	private HisWs mHisWs=new HisWs();
	
    public List<XtBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100164");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("CZYNBBM", pubParamBean.getA01());
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	cxOutBean = mHisWs.funSelect(cxInBean);
    	if(cxOutBean.isState()){
    		System.out.println("query success");
    		List<Map<String, String>> list = cxOutBean.getRow01();
    		if(list==null){
    			return null;
    		}
    		return listToXtBeanList(list);
    	}else{
    		return null;
    	}
    }
    
    //distinct XTBM,XTMC,MKBM,MKMC,MKLX,GNNBBM,GNLX,GNBM,GNMC,CDMC,
  	//CKMC,CKCS,isnull(DBTJ,"") as VURL,XH,MKXH
    public List<XtBean> listToXtBeanList(List<Map<String, String>> listIn){
    	List<Map<String, String>> listMk = listIn;
    	List<Map<String, String>> listGn = listIn;
    	List<XtBean> listXtBean = new ArrayList<XtBean>();
    	List<MkBean> listMkBean = new ArrayList<MkBean>();
    	List<GnBean> listGnBean = new ArrayList<GnBean>();
    	String xtbm,xtmc,tmpXtbm;
    	String mkbm,mkmc,tmpMkbm;
    	String curMkbm,curXtbm;
    	boolean b = false;
    	GnBean gnBean;
    	MkBean mkBean;
    	XtBean xtBean;
    	for(int i=0; i<listIn.size(); i++){
    		xtBean = new XtBean();
    		xtbm = listIn.get(i).get("XTBM");
    		xtmc = listIn.get(i).get("XTMC");
    		//消除重复的系统
    		b = false;
    		for(int ii=0; ii<listXtBean.size(); ii++){
    			tmpXtbm = listXtBean.get(ii).getXtbm();
    			if(xtbm.equals(tmpXtbm)) b=true;
    		}
    		if(b) continue;
    		listMkBean = new ArrayList<MkBean>();
    		for(int j=0; j<listMk.size(); j++){
    			curXtbm = listMk.get(j).get("XTBM");
    			if(xtbm.equals(curXtbm)){
    				mkBean = new MkBean();
        			mkmc = listMk.get(j).get("MKMC");
        			mkbm = listIn.get(j).get("MKBM");
        			//消除重复的模块
            		b = false;
            		for(int jj=0; jj<listMkBean.size(); jj++){
            			tmpMkbm = listMkBean.get(jj).getMkbm();
            			if(mkbm.equals(tmpMkbm)) b=true;
            		}
            		if(b) continue;
        			listGnBean = new ArrayList<GnBean>();
            		for(int k=0; k<listGn.size(); k++){
            			curMkbm = listGn.get(k).get("MKBM");
            			if(mkbm.equals(curMkbm)){
            				gnBean = new GnBean();
            				gnBean.setText(listGn.get(k).get("GNMC"));
            				gnBean.setUrl(listGn.get(k).get("VURL"));
            				listGnBean.add(gnBean);
            			}
            		}
            		mkBean.setState("closed");
            		mkBean.setMkbm(mkbm);
            		mkBean.setText(mkmc);
            		mkBean.setChildren(listGnBean);
            		listMkBean.add(mkBean);
    			}
    		}
    		xtBean.setState("closed");
    		xtBean.setXtbm(xtbm);
    		xtBean.setText(xtmc);
    		xtBean.setChildren(listMkBean);
    		listXtBean.add(xtBean);
    	}
		return listXtBean;
    }
    
}
