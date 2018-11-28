package gcp.springmvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.bean.ResProjectDetailBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

public class ResProjectDetailManager {
	
	private HisWs mHisWs=new HisWs();
	
    public List<ResProjectDetailBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid("10100171");
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
    	List<ResProjectDetailBean> listRPB = new ArrayList<ResProjectDetailBean>();
    	cxOutBean = mHisWs.funSelect(cxInBean);
    	if(cxOutBean.isState()){
    		System.out.println("query success");
    		List<Map<String, String>> list = cxOutBean.getRow01();
    		if(list==null){
    			return null;
    		}
    		for(int i=0; i<list.size(); i++){
    			listRPB.add( rowToBean(list.get(i)) );
    		}
    		return listRPB;
    	}else{
    		return null;
    	}
    }
    
    public ResProjectDetailBean rowToBean(Map<String, String> map){
    	ResProjectDetailBean bean = new ResProjectDetailBean();
    	bean.setXMBM(map.get("XMBM"));
    	bean.setCFDAH(map.get("CFDAH"));
    	bean.setYWMC(map.get("YWMC"));
    	bean.setYWJX(map.get("YWJX"));
    	bean.setSYZ(map.get("SYZ"));
    	bean.setZCFL(map.get("ZCFL"));
    	bean.setSYFQ(map.get("SYFQ"));
    	bean.setLXLB(map.get("LXLB"));
    	bean.setSCXS(map.get("SCXS"));
    	bean.setSBZ(map.get("SBZ"));
    	bean.setSBZLXDH(map.get("SBZLXDH"));
    	bean.setCRO(map.get("CRO"));
    	bean.setCROLXDH(map.get("CROLXDH"));
    	bean.setZZDW(map.get("ZZDW"));
    	bean.setZZDWYJY(map.get("ZZDWYJY"));
    	bean.setBZXCDJY(map.get("BZXCDJY"));
    	bean.setBZXCDJYYJY(map.get("BZXCDJYYJY"));
    	bean.setJHLS(map.get("JHLS"));
    	bean.setYYLS(map.get("YYLS"));
    	bean.setZGYS(map.get("ZGYS"));
    	bean.setZGYSXM(map.get("ZGYSXM"));
    	bean.setZGKS(map.get("ZGKS"));
    	bean.setZGKSMC(map.get("ZGKSMC"));
		return bean;
    }

    public SaveOutputBean update(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101914");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//update KYXM_XMML_GCP set CFDAH=?,YWMC=?,YWJX=?,SYZ=?,ZCFL=?,SYFQ=?,
    	//LXLB=?,SCXS=?,SBZ=?,SBZLXDH=?,CRO=?,CROLXDH=?,ZZDW=?,ZZDWYJY=?,BZXCDJY=?,
    	//BZXCDJYYJY=?,JHLS=?,YYLS=? from KYXM_XMML_GCP where XMBM=?
    	vmap.put("XMBM", pubParamBean.getA01());
    	vmap.put("CFDAH", pubParamBean.getA02());
    	vmap.put("YWMC", pubParamBean.getA03());
    	vmap.put("YWJX", pubParamBean.getA04());
    	vmap.put("SYZ", pubParamBean.getA05());
    	vmap.put("ZCFL", pubParamBean.getA06());
    	vmap.put("SYFQ", pubParamBean.getA07());
    	vmap.put("LXLB", pubParamBean.getA08());
    	vmap.put("SCXS", pubParamBean.getA09());
    	vmap.put("SBZ", pubParamBean.getA10());
    	vmap.put("SBZLXDH", pubParamBean.getA11());
    	vmap.put("CRO", pubParamBean.getA12());
    	vmap.put("CROLXDH", pubParamBean.getA13());
    	vmap.put("ZZDW", pubParamBean.getA14());
    	vmap.put("ZZDWYJY", pubParamBean.getA15());
    	vmap.put("BZXCDJY", pubParamBean.getA16());
    	vmap.put("BZXCDJYYJY", pubParamBean.getA17());
    	vmap.put("JHLS", pubParamBean.getA18());
    	vmap.put("YYLS", pubParamBean.getA19());
    	vmap.put("ZGYS", pubParamBean.getA20());
    	vmap.put("ZGKS", pubParamBean.getA21());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	System.out.println(">>>>>>>>>>>"+saveInBean.funGetJson());
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }

}
