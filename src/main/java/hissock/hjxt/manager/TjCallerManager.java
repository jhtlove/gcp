package hissock.hjxt.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcp.springmvc.bean.PubParamBean;
import gcp.springmvc.bean.ResProjectBean;
import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.busiws.SaveInputBean;
import ihis.busiws.SaveOutputBean;

/**
 * 体检呼叫器服务
 * @author Team
 *
 */
public class TjCallerManager {
	
	private HisWs mHisWs=new HisWs();
	
	/** 考虑是服务器主动推送，还是呼叫器端主动查询 */
	/**  select ID,PDRQ,ZYBM,HYID,HYBM,HYSJD,BC,HYLX,BRLX,BRXM,BRXB,BRNL,SQKSDM,SQKS,YYSJ,BRID,ZT,XH,SCSJ FROM BOOKING_PDXX */
    public List<ResProjectBean> query(CxInputBean cxInBean, PubParamBean pubParamBean){
    	String whereTj;
    	String selectId;
    	if(pubParamBean.getA30().equals("23144") || pubParamBean.getA30().equals("02207") || pubParamBean.getA30().equals("23271") || pubParamBean.getA30().equals("02244") || pubParamBean.getA30().equals("23889")|| pubParamBean.getA30().equals("23888")){
    		whereTj = "1=1";
    		selectId = "10100180";
    	}else{
    		whereTj = " KYXM_XMML_CYYG.CZYDM='"+pubParamBean.getA30()+"'";
    		selectId = "10100165";
    	}
    	CxOutputBean cxOutBean = new CxOutputBean();
    	cxInBean.setBusiid(selectId);
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	if(!pubParamBean.getA01().equals("")){
    		whereTj += " and KYXM_XMML.XMMC like '%"+pubParamBean.getA01()+"%'";
    	}
    	if(!pubParamBean.getA02().equals("") && !pubParamBean.getA02().equals("all")){
    		whereTj += " and KYXM_XMML.ZT="+pubParamBean.getA02();
    	}
    	hmap.put("wheretj", whereTj);
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	System.out.println("query in:"+cxInBean.funGetJson());
    	List<ResProjectBean> listRPB = new ArrayList<ResProjectBean>();
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
    
    public ResProjectBean rowToBean(Map<String, String> map){
    	ResProjectBean rpb = new ResProjectBean();
		rpb.setXMBM(map.get("XMBM"));
		rpb.setKYDW(map.get("KYDW"));
		rpb.setKYDWMC(map.get("KYDWMC"));
		rpb.setXMLX(map.get("XMLX"));
		rpb.setXMMC(map.get("XMMC"));
		rpb.setPYM(map.get("PYM"));
		rpb.setWBM(map.get("WBM"));
		rpb.setZT(map.get("ZT"));
		rpb.setYXRQ(map.get("YXRQ"));
		rpb.setZBRQ(map.get("ZBRQ"));
		rpb.setFYLX(map.get("FYLX"));
		return rpb;
    }

    public SaveOutputBean add(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101909");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	//KYXM_XMML(XMBM,KYDW,XMLX,XMMC,PYM,WBM,ZT,YXRQ,ZBRQ,FYLX)
    	vmap.put("XMMC", pubParamBean.getA01());//项目名称
    	vmap.put("KYDW", pubParamBean.getA02());//科研单位
    	vmap.put("XMLX", pubParamBean.getA03());//项目类型
    	vmap.put("YXRQ", pubParamBean.getA04());//立项日期
    	vmap.put("FYLX", pubParamBean.getA05());//费用类型
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }

    /** 修改---呼叫时修改排队记录状态 */
    //select ID,PDRQ,ZYBM,HYID,HYBM,HYSJD,BC,HYLX,BRLX,BRXM,BRXB,BRNL,SQKSDM,SQKS,YYSJ,BRID,ZT,XH,SCSJ FROM BOOKING_PDXX
    public SaveOutputBean update(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101910");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	List<Map<String,String>> xml1 = new ArrayList<Map<String,String>>();
    	Map<String,String> vmap = new HashMap<String, String>();
    	vmap.put("XMBM", pubParamBean.getA01());
    	vmap.put("XMMC", pubParamBean.getA02());
    	vmap.put("KYDW", pubParamBean.getA03());
    	vmap.put("XMLX", pubParamBean.getA04());
    	vmap.put("YXRQ", pubParamBean.getA05());
    	vmap.put("FYLX", pubParamBean.getA06());
    	xml1.add(vmap);
    	saveInBean.setXml1(xml1);
    	System.out.println(">>>>>>>>>>>"+saveInBean.funGetJson());
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
    
    public SaveOutputBean delete(SaveInputBean saveInBean, PubParamBean pubParamBean){
    	saveInBean.setBusiid("20101911");
    	saveInBean.setDbname("his_gcp");
    	saveInBean.setIywlx(1);
    	saveInBean.setArg1(pubParamBean.getA01());
    	SaveOutputBean sopb;
    	sopb = mHisWs.funSave(saveInBean);
    	return sopb;
    }
    
}
