package ihis.busiws;

import ihis.pubfun.Pfun;

public class AppYwcl {
	public static AppWs appws;
	private String pvalid="($_$)zxyy($_$)";
	private Pfun pfun=new Pfun();
	public AppYwcl(){
		if(appws==null){
			AppService app=new AppService();
			appws=app.getAppWsPort();			
		}
	}
	/*
	 * 登录数据库
	 */
	public String funLogin(String userid,String password){
		String srtn;
		String scheckcode,smd5m;
		scheckcode=pfun.funMd(userid,pvalid);
		smd5m=pfun.funMd(password,"");
		srtn=appws.applogin(scheckcode,userid,smd5m,"APP");
		return srtn;
	}
	/*
	 * 查询数据
	 */	
	public String funSelect(String userid,String password,String jsonstr){
		String srtn,smd5m;
		String scheckcode;
		scheckcode=pfun.funMd(userid,pvalid);
		smd5m=pfun.funMd(password,"");
		srtn=appws.appselect(scheckcode,userid,smd5m,jsonstr);
		return srtn;
	}
	/*
	 * 保存到数据库中
	 */
	public String funSave(String userid,String password,String sbusid,int iczlx,String jsonstr){
		String srtn,smd5m;
		String scheckcode;
		scheckcode=pfun.funMd(userid,pvalid);
		smd5m=pfun.funMd(password,"");
		srtn=appws.busijson(scheckcode,"101",userid,smd5m,"","his_clinic",sbusid,iczlx,jsonstr);
		return srtn;
	}
}