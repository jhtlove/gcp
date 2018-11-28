package ihis.busiws;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ihis.pubfun.Pfun;
import net.sf.json.JSONObject;

public class HisWs {
	private AppYwcl appywcl=new AppYwcl();
	private Pfun mPfun = new Pfun();
	/*
	 * 登录业务
	 */
	public SaveOutputBean funLogin(String userid,String password){
		SaveOutputBean outbean=new SaveOutputBean();
		String outstr;
		outstr=appywcl.funLogin(userid, password);
		//System.out.println(outstr);
		//登录结果转成JAVABEAN
		outbean=funSaveJson(outstr);
		return outbean;
	}
	/*
	 * 查询业务
	 */	
	public CxOutputBean funSelect(CxInputBean inputBean){
		CxOutputBean outbean=new CxOutputBean();
		String outstr;
		outstr=appywcl.funSelect(inputBean.getUserid(),inputBean.getPassword(),inputBean.funGetJson()) ;
		//System.out.println(outstr);
		//查询结果转成JAVABEAN
		outbean=funCxJgJson(outstr);
		return outbean;
	}
	/*
	 * 保存业务
	 */	
	public SaveOutputBean funSave(SaveInputBean inputBean){
		SaveOutputBean outbean=new SaveOutputBean();
		String outstr;		 
		outstr=appywcl.funSave(inputBean.getUserid(),inputBean.getPassword(),inputBean.getBusiid(),inputBean.getIywlx(),inputBean.funGetJson());
		//System.out.println(outstr);
		//保存结果转成JAVABEAN
		outbean=funSaveJson(outstr);
		return outbean;
	}
	//查询结果转成JAVABEAN
	private CxOutputBean funCxJgJson(String jsonStr){
		String sstate,scur;
		CxOutputBean outbean=new CxOutputBean();
		JsonObject jsonObj = new JsonParser().parse(jsonStr).getAsJsonObject();
		sstate = jsonObj.get("state").getAsString();
		if(Boolean.valueOf(sstate)==false){			
			scur = jsonObj.get("error").getAsString();
			outbean.setState(false);
			outbean.setError(scur);
		}else{
			outbean.setState(true);
			if(jsonObj.has("row01")){
				outbean.setRow01(mPfun.jsonObjToListGson(jsonObj,"row01"));
			}
			if(jsonObj.has("row02")){
				outbean.setRow02(mPfun.jsonObjToListGson(jsonObj,"row02"));
			}
			if(jsonObj.has("row03")){
				outbean.setRow03(mPfun.jsonObjToListGson(jsonObj,"row03"));
			}
			if(jsonObj.has("row04")){
				outbean.setRow04(mPfun.jsonObjToListGson(jsonObj,"row04"));
			}
			if(jsonObj.has("row05")){
				outbean.setRow05(mPfun.jsonObjToListGson(jsonObj,"row05"));
			}
			if(jsonObj.has("row06")){
				outbean.setRow06(mPfun.jsonObjToListGson(jsonObj,"row06"));
			}
		}
		return outbean;
	}
	//保存返回值转成JAVABEAN
	private SaveOutputBean funSaveJson(String jsonStr){
		String sstate,scur;
		SaveOutputBean outbean=new SaveOutputBean();
		JSONObject jsonobject;
		jsonobject= JSONObject.fromObject(jsonStr);
		sstate=jsonobject.getString("state");
		if(Boolean.valueOf(sstate)==false){			
			scur=jsonobject.getString("error");
			outbean.setState(false);
			outbean.setErrorMsg(scur);
		}else{
			outbean.setState(true);
			outbean.setA01(jsonobject.getString("A01"));
			outbean.setA02(jsonobject.getString("A02"));
			outbean.setA03(jsonobject.getString("A03"));
			outbean.setA04(jsonobject.getString("A04"));
			outbean.setA05(jsonobject.getString("A05"));
			outbean.setA06(jsonobject.getString("A06"));
			outbean.setA07(jsonobject.getString("A07"));
			outbean.setA08(jsonobject.getString("A08"));
			outbean.setA09(jsonobject.getString("A09"));
			outbean.setA10(jsonobject.getString("A10"));
			outbean.setA11(jsonobject.getString("A11"));
			outbean.setA12(jsonobject.getString("A12"));
			outbean.setA13(jsonobject.getString("A13"));
			outbean.setA14(jsonobject.getString("A14"));
			outbean.setA15(jsonobject.getString("A15"));
			outbean.setA16(jsonobject.getString("A16"));
			outbean.setA17(jsonobject.getString("A17"));
			outbean.setA18(jsonobject.getString("A18"));
			outbean.setA19(jsonobject.getString("A19"));
			outbean.setA20(jsonobject.getString("A20"));
			outbean.setA21(jsonobject.getString("A21"));
			outbean.setA22(jsonobject.getString("A22"));
			outbean.setA23(jsonobject.getString("A23"));
			outbean.setA24(jsonobject.getString("A24"));
			outbean.setA25(jsonobject.getString("A25"));
			outbean.setA26(jsonobject.getString("A26"));
			outbean.setA27(jsonobject.getString("A27"));
			outbean.setA28(jsonobject.getString("A28"));
			outbean.setA29(jsonobject.getString("A29"));
			outbean.setA30(jsonobject.getString("A30"));
			
			outbean.setA31(jsonobject.getString("A31"));
			outbean.setA32(jsonobject.getString("A32"));
			outbean.setA33(jsonobject.getString("A33"));
			outbean.setA34(jsonobject.getString("A34"));
			outbean.setA35(jsonobject.getString("A35"));
			outbean.setA36(jsonobject.getString("A36"));
			outbean.setA37(jsonobject.getString("A37"));
			outbean.setA38(jsonobject.getString("A38"));
			outbean.setA39(jsonobject.getString("A39"));
			outbean.setA40(jsonobject.getString("A40"));
			
			outbean.setA41(jsonobject.getString("A41"));
			outbean.setA42(jsonobject.getString("A42"));
			outbean.setA43(jsonobject.getString("A43"));
			outbean.setA44(jsonobject.getString("A44"));
			outbean.setA45(jsonobject.getString("A45"));
			outbean.setA46(jsonobject.getString("A46"));
			outbean.setA47(jsonobject.getString("A47"));
			outbean.setA48(jsonobject.getString("A48"));
			outbean.setA49(jsonobject.getString("A49"));
			outbean.setA50(jsonobject.getString("A50"));			
		}
		return outbean;
	}
}