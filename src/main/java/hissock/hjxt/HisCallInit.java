package hissock.hjxt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import ihis.busiws.CxInputBean;
import ihis.busiws.CxOutputBean;
import ihis.busiws.HisWs;
import ihis.busiws.IhisParamer;

/**
 * jboss启动时，自动执行初始化程序
 * 
 */
public class HisCallInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HisWs mHisWs=new HisWs();
	//资源队列（TJ1001，TJ1002，TJ1003）
	public static List<Map<String, String>> resQueueList = null;
	
	public void init() throws ServletException {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<auto init...>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<auto init...>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<auto init...>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<auto init...>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<auto init...>>>>>>>>>>>>>>>>>>>>>>>>");
		if(resQueueList==null){
			resQueueList = queryResourcesQueue();
		}
    }

    /** 查询资源队列 */
    private static List<Map<String, String>> queryResourcesQueue(){
    	CxOutputBean cxOutBean = new CxOutputBean();
    	CxInputBean cxInBean = new CxInputBean();
    	cxInBean.setUserid(IhisParamer.iuserid);
    	cxInBean.setPassword(IhisParamer.password);
    	cxInBean.setBusiid("10100178");
    	cxInBean.setOpens("idontknow");
    	cxInBean.setPages("0");
    	cxInBean.setRows("0");
    	cxInBean.setSqlcnt("1");
    	List<Map<String, String>> xml01 = new ArrayList<Map<String,String>>();
    	HashMap<String, String> hmap = new HashMap<String, String>();
    	hmap.put("KYBZ", "1");
    	xml01.add(hmap);
    	cxInBean.setXml01(xml01);
    	List<Map<String, String>> rtnList = null;
    	cxOutBean = mHisWs.funSelect(cxInBean);
    	if(cxOutBean.isState()){
    		System.out.println("查询资源队列");
    		rtnList = cxOutBean.getRow01();
    		return rtnList;
    	}else{
    		return null;
    	}
    }
    

}
