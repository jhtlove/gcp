package ihis.busiws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CxInputBean {
	private String busiid="";
	private String userid="";
	private String password="";
	private String opens="";
	private String pages="0";
	private String rows="0";
	private String sqlcnt="";
	private List<Map<String,String>> xml01=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> xml02=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> xml03=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> xml04=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> xml05=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> xml06=new ArrayList<Map<String,String>>();
	public String getBusiid() {
		return busiid;
	}
	public void setBusiid(String busiid) {
		this.busiid = busiid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOpens() {
		return opens;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setOpens(String opens) {
		this.opens = opens;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getSqlcnt() {
		return sqlcnt;
	}
	public void setSqlcnt(String sqlcnt) {
		this.sqlcnt = sqlcnt;
	}
	public List<Map<String, String>> getXml01() {
		return xml01;
	}
	public void setXml01(List<Map<String, String>> xml01) {
		this.xml01 = xml01;
	}
	public List<Map<String, String>> getXml02() {
		return xml02;
	}
	public void setXml02(List<Map<String, String>> xml02) {
		this.xml02 = xml02;
	}
	public List<Map<String, String>> getXml03() {
		return xml03;
	}
	public void setXml03(List<Map<String, String>> xml03) {
		this.xml03 = xml03;
	}
	public List<Map<String, String>> getXml04() {
		return xml04;
	}
	public void setXml04(List<Map<String, String>> xml04) {
		this.xml04 = xml04;
	}
	public List<Map<String, String>> getXml05() {
		return xml05;
	}
	public void setXml05(List<Map<String, String>> xml05) {
		this.xml05 = xml05;
	}
	public List<Map<String, String>> getXml06() {
		return xml06;
	}
	public void setXml06(List<Map<String, String>> xml06) {
		this.xml06 = xml06;
	}
	public String funGetJson(){
		String srtn;
		JSONObject jsonObject2 = new JSONObject();
		JSONArray jsarr;
		try{
			jsonObject2.put("busiid", busiid);
			jsonObject2.put("userid", userid);
			jsonObject2.put("opens", opens);
			jsonObject2.put("pages", pages);
			jsonObject2.put("rows", rows);
			jsonObject2.put("sqlcnt", sqlcnt);
			
			if(xml01.size()>0){
				jsarr=JSONArray.fromObject(xml01);
				jsonObject2.element("xml01", jsarr);
			}
			if(xml02.size()>0){
				jsarr=JSONArray.fromObject(xml02);
				jsonObject2.element("xml02", jsarr);
			}
			if(xml03.size()>0){
				jsarr=JSONArray.fromObject(xml03);
				jsonObject2.element("xml03", jsarr);
			}
			if(xml04.size()>0){
				jsarr=JSONArray.fromObject(xml04);
				jsonObject2.element("xml04", jsarr);
			}
			if(xml05.size()>0){
				jsarr=JSONArray.fromObject(xml05);
				jsonObject2.element("xml05", jsarr);
			}
			if(xml06.size()>0){
				jsarr=JSONArray.fromObject(xml06);
				jsonObject2.element("xml06", jsarr);
			}
			srtn=jsonObject2.toString();
			return srtn;
		}finally{
			jsarr=null;
			jsonObject2=null;
			srtn=null;
		}		
	}
	
}
