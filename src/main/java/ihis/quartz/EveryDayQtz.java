//package ihis.quartz;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//
//import hissock.hjxt.HisSocketBean;
//import hissock.hjxt.hisWebSocketConnectionCallback;
//import ihis.busiws.CxInputBean;
//import ihis.busiws.CxOutputBean;
//import ihis.busiws.HisWs;
//import ihis.busiws.IhisParamer;
//import ihis.pubfun.Pfun;
//import io.undertow.websockets.core.WebSockets;
//@Service
//public class EveryDayQtz {
//	private HisWs mHisWs=new HisWs();
//	private Pfun mPfun = new Pfun();
//	private static int count = 0;
//	
//	/**
//	 * 定时排队和已呼未到的
//	 */
//	public void funHjxxSend(){
////		System.out.println("-----------------pdxx msg-----------------"
////				+ "clients count:"+hisWebSocketConnectionCallback.clientList.size());
//		//count++;
//		HisSocketBean aWebSocketBean;
//		String tvid;
//		String queryTvid;
//		String sJson = "";
//		String sJson1 = "";
//		String sJson2 = "";
//		int itag = 0;
//		String sXml1 = "";
//		String sXml2 = "";
//		Map<String,List<Map<String, String>>> queryDataMap = new HashMap<String,List<Map<String, String>>>();
//		for(int i=0;i<hisWebSocketConnectionCallback.clientList.size();i++) {
//			aWebSocketBean =hisWebSocketConnectionCallback.clientList.get(i);
//			tvid = aWebSocketBean.getTvId();
//			queryDataMap = queryQueueRecord(tvid);
//			itag = 0;
//			if(queryDataMap!=null){
//				if(queryDataMap.get("xml1")!=null){
//					sXml1 = mPfun.listToJsonByGson(queryDataMap.get("xml1"));
//					sJson1 = "\"xml1\":"+ sXml1;
////					if(sXml1.equals("[]")){
////						itag++;
////					}
//				}else{
//					sJson1 = "\"xml1\":[]";
////					itag++;
//				}
//				if(queryDataMap.get("xml2")!=null){
//					sXml2 = mPfun.listToJsonByGson(queryDataMap.get("xml2"));
//					sJson2 = "\"xml2\":"+ sXml2;
////					if(sXml2.equals("[]")){
////						itag++;
////					}
//				}else{
//					sJson2 = "\"xml2\":[]";
////					itag++;
//				}
////				if(itag<2){
////					sJson = "{"+ sJson1 + "," + sJson2 +"}";
////					WebSockets.sendText(sJson, aWebSocketBean.getWebSocket(), null);
////				}
//				sJson = "{"+ sJson1 + "," + sJson2 +"}";
//				WebSockets.sendText(sJson, aWebSocketBean.getWebSocket(), null);
//			}
//			
//			/** 服务器主动关闭websocket连接
//			try {
//				if(count>=5){
//					aWebSocketBean.getWebSocket().close();
//					hisWebSocketConnectionCallback.clientList.remove(i);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			*/
//		}
//	}
//    /** 查询队列数据 */
//    private Map<String,List<Map<String, String>>> queryQueueRecord(String zybm){
//    	CxOutputBean cxOutBean = new CxOutputBean();
//    	CxInputBean cxInBean = new CxInputBean();
//    	Date date = new Date();
//    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd");
//    	String today = dateFormat.format(date);
//    	cxInBean.setUserid(IhisParamer.iuserid);
//    	cxInBean.setPassword(IhisParamer.password);
//    	cxInBean.setBusiid("10100179");
//    	cxInBean.setOpens("idontknow");
//    	cxInBean.setPages("0");
//    	cxInBean.setRows("0");
//    	cxInBean.setSqlcnt("2");
//    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
//    	List<Map<String, String>> xml02 = new ArrayList<Map<String,String>>();
//    	HashMap<String, String> hmap1 = new HashMap<String, String>();
//    	HashMap<String, String> hmap2 = new HashMap<String, String>();
//    	hmap1.put("ZYBM", zybm);
//    	hmap1.put("PDRQ", today);
//    	xml01.add(hmap1);
//    	hmap2.put("ZYBM", zybm);
//    	hmap2.put("PDRQ", today);
//    	xml02.add(hmap2);
//    	cxInBean.setXml01(xml01);
//    	cxInBean.setXml02(xml02);
//    	Map<String,List<Map<String, String>>> rtnMap = new HashMap<String,List<Map<String, String>>>();
//    	cxOutBean = mHisWs.funSelect(cxInBean);
//    	if(cxOutBean.isState()){
//    		System.out.println("xml1:"+cxOutBean.getRow01().size());
//    		rtnMap.put("xml1", cxOutBean.getRow01());
//    		System.out.println("xml2:"+cxOutBean.getRow02().size());
//    		rtnMap.put("xml2", cxOutBean.getRow02());
//    		return rtnMap;
//    	}else{
//    		System.out.println("Queue select error!!!");
//    		return null;
//    	}
//    }
//    
//    
//	/**
//	 * 定时发送正在呼叫的
//	 */
//	public void funCallingSend(){
//		//System.out.println("-----------------calling send -----------------");
//		HisSocketBean aWebSocketBean;
//		String tvid;
//		String sJson = "";
//		String sXml1 = "";
//		Map<String,List<Map<String, String>>> queryDataMap = new HashMap<String,List<Map<String, String>>>();
//		for(int i=0;i<hisWebSocketConnectionCallback.clientList.size();i++) {
//			aWebSocketBean =hisWebSocketConnectionCallback.clientList.get(i);
//			tvid = aWebSocketBean.getTvId();
//			queryDataMap = queryCalling(tvid);
//			if(queryDataMap!=null){
//				if(queryDataMap.get("calling")!=null){
//					sXml1 = mPfun.listToJsonByGson(queryDataMap.get("calling"));
//					sJson = "{\"calling\":"+ sXml1 +"}";
//					if(!sXml1.equals("[]")){
//						//WebSockets.sendText(sJson, aWebSocketBean.getWebSocket(), null);
//					}
//				}else{
//					sJson = "{\"calling\":[]}";
//				}
//				WebSockets.sendText(sJson, aWebSocketBean.getWebSocket(), null);
//			}
//		}
//	}
//    /** 查询队列数据 */
//    private Map<String,List<Map<String, String>>> queryCalling(String zybm){
//    	CxOutputBean cxOutBean = new CxOutputBean();
//    	CxInputBean cxInBean = new CxInputBean();
//    	Date date = new Date();
//    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd");
//    	String today = dateFormat.format(date);
//    	cxInBean.setUserid(IhisParamer.iuserid);
//    	cxInBean.setPassword(IhisParamer.password);
//    	cxInBean.setBusiid("10100182");
//    	cxInBean.setOpens("idontknow");
//    	cxInBean.setPages("0");
//    	cxInBean.setRows("0");
//    	cxInBean.setSqlcnt("1");
//    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
//    	HashMap<String, String> hmap1 = new HashMap<String, String>();
//    	hmap1.put("ZYBM", zybm);
//    	hmap1.put("PDRQ", today);
//    	xml01.add(hmap1);
//    	cxInBean.setXml01(xml01);
//    	Map<String,List<Map<String, String>>> rtnMap = new HashMap<String,List<Map<String, String>>>();
//    	cxOutBean = mHisWs.funSelect(cxInBean);
//    	if(cxOutBean.isState()){
//    		System.out.println("calling:"+cxOutBean.getRow01().size());
//    		rtnMap.put("calling", cxOutBean.getRow01());
//    		return rtnMap;
//    	}else{
//    		System.out.println("calling select error!!!");
//    		return null;
//    	}
//    }
//	
//}