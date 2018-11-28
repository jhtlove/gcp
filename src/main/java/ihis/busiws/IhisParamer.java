package ihis.busiws;

public class IhisParamer {
	public static String ihisyyid="10006";			//医院编码
	public static String ihisyymc="长沙市中心医院";			//医院名称
	public static String ihisurl="http://192.168.3.18:8080/Services/AppWs?wsdl";			//中间件IP
	public static String iuserid="3626";
	public static String password="111";
	public static String passwordmd5="9d8a121ce581499d";
	public IhisParamer(){
	}

	public static void funInit(){
//		iuserid=pfun.funGetHisProperties("JK_USERID");
//		iusermm=pfun.funGetHisProperties("JK_USERMM");
//		ihisurl=pfun.funGetHisProperties("JK_HISWS");
//		pfun.WriteLogA("JK_USERID:"+iuserid);
//		pfun.WriteLogA("JK_USERMM:"+iusermm);
//		pfun.WriteLogA("JK_HISWS:"+ihisurl);
//		
//		if(g_appser==null){
//			g_appser = new AppService();		
//			g_appws = g_appser.getAppWsPort();
//		}
	}
}