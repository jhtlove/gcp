package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.bean.StageAdviceBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

public class StageAdviceManager {
	private HisWs mHisWs=new HisWs();
	private List<Map<String,String>> mFrequency = null;
	
    public List<StageAdviceBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	if(mFrequency==null){
	    	CxInputBean cxIn = new CxInputBean();
	    	cxIn.setUserid(cxInBean.getUserid());
	    	cxIn.setPassword(cxInBean.getPassword());
	    	mFrequency = queryFrequency(cxIn);
    	}
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100174");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("GYID", pubParamBean.getA01());
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	hmap = null;
    	xml01 = null;
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<StageAdviceBean> listBean = new ArrayList<StageAdviceBean>();
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
    
    public StageAdviceBean rowToBean(Map<String, String> map){
    	StageAdviceBean bean = new StageAdviceBean();
    	bean.setID(map.get("ID"));
    	bean.setGYID(map.get("GYID"));
    	bean.setXH(map.get("XH"));
    	bean.setYZLB(map.get("YZLB"));
    	bean.setYZDM(map.get("YZDM"));
    	bean.setYZMC(map.get("YZMC"));
    	bean.setZXKS(map.get("ZXKS"));
    	bean.setZXKSMC(map.get("ZXKSMC"));
    	bean.setBZXM(map.get("BZXM"));
    	bean.setKYBZ(map.get("KYBZ"));
    	/*bean.setJL(map.get("JL"));
    	bean.setYF(map.get("YF"));
    	bean.setYFMC(map.get("YFMC"));
    	bean.setYFSM(map.get("YFSM"));
    	bean.setPD(map.get("PD"));
    	String dmmc_ldw = KMP(mFrequency,map.get("PD"));
    	if(dmmc_ldw.length()<=0){
    		bean.setPDMC("");
        	bean.setPDLDW("");
    	}else{
    		String [] array_dmmc_ldw = dmmc_ldw.split(",");
    		if(array_dmmc_ldw.length==2){
    			bean.setPDMC(array_dmmc_ldw[0]);
    			bean.setPDLDW(array_dmmc_ldw[1]);
    		}else{
    			bean.setPDMC("");
            	bean.setPDLDW("");
    		}
    	}
    	bean.setTS(map.get("TS"));
    	bean.setZSL(map.get("ZSL"));
    	*/
    	bean.setJZFS(map.get("JZFS"));
    	bean.setYZJE(map.get("YZJE"));
		return bean;
    }
    
    private String KMP(List<Map<String, String>> list,String pd){
    	if(list==null || pd==null || pd.length()<=0) return "";
    	String pd1;
    	for(int i=0; i<list.size(); i++){
    		pd1 = list.get(i).get("DM");
    		if(pd1.equals(pd)){
    			return list.get(i).get("DMMC")+","+list.get(i).get("LDW");
    		}
    	}
    	return "";
    }

    /** 查询频度 */
    private List<Map<String, String>> queryFrequency(CxInputBean cxInBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100176");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("BZ", "1");
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	List<Map<String, String>> rtnList = null;
    	cxOutBean = mHisWs.funSelect(cxInBean);
    	if(cxOutBean.isState()){
    		System.out.println("频度查询成功");
    		rtnList = cxOutBean.getRow01();
    		return rtnList;
    	}else{
    		return null;
    	}
    }
     
    public SaveOutputBean add(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101918");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//(ID,GYID,XH,YZLB,YZDM,YZMC,ZXKS,BZXM,KYBZ,JL,YF,YFSM,PD,TS,ZSL,JZFS,YZJE)
    	vmap.put("ID", pubParamBean.getA01());
    	vmap.put("GYID", pubParamBean.getA02());
    	vmap.put("XH", pubParamBean.getA03());
    	vmap.put("YZLB", pubParamBean.getA04());
    	vmap.put("YZDM", pubParamBean.getA05());
    	vmap.put("JZFS", pubParamBean.getA06());
    	vmap.put("BZXM", pubParamBean.getA07());
    	vmap.put("KYBZ", pubParamBean.getA08());
    	vmap.put("YZMC", pubParamBean.getA09());
    	vmap.put("ZXKS", pubParamBean.getA10());
    	/*
    	vmap.put("JL", pubParamBean.getA11());
    	vmap.put("YF", pubParamBean.getA12());
    	vmap.put("YFSM", pubParamBean.getA13());
    	vmap.put("PD", pubParamBean.getA14());
    	vmap.put("TS", pubParamBean.getA15());
    	vmap.put("ZSL", pubParamBean.getA16());
    	*/
    	vmap.put("YZJE", pubParamBean.getA11());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }

    public SaveOutputBean update(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101919");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//set YZJE=?,XH=?,YZLB=?,YZDM=?,YZMC=?,ZXKS=?,BZXM=?,KYBZ=?,JL=?,YF=?,YFSM=?,PD=?,TS=?,ZSL=? where ID=? AND GYID=?
    	vmap.put("ID", pubParamBean.getA01());
    	vmap.put("GYID", pubParamBean.getA02());
    	vmap.put("XH", pubParamBean.getA03());
    	vmap.put("YZLB", pubParamBean.getA04());
    	vmap.put("YZDM", pubParamBean.getA05());
    	vmap.put("JZFS", pubParamBean.getA06());
    	vmap.put("BZXM", pubParamBean.getA07());
    	vmap.put("KYBZ", pubParamBean.getA08());
    	vmap.put("YZMC", pubParamBean.getA09());
    	vmap.put("ZXKS", pubParamBean.getA10());
    	/*
    	vmap.put("JL", pubParamBean.getA11());
    	vmap.put("YF", pubParamBean.getA12());
    	vmap.put("YFSM", pubParamBean.getA13());
    	vmap.put("PD", pubParamBean.getA14());
    	vmap.put("TS", pubParamBean.getA15());
    	vmap.put("ZSL", pubParamBean.getA16());
    	*/
    	vmap.put("YZJE", pubParamBean.getA11());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	System.out.println(">>>>>>>>>>>"+saveInBean.funGetJson());
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
    
    public SaveOutputBean delete(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101920");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	saveInBean.setArg1(pubParamBean.getA01());//id
    	saveInBean.setArg2(pubParamBean.getA02());//gyid
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
}
