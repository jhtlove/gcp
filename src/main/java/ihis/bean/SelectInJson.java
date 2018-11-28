package ihis.bean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class  SelectInJson{
	private String busiid="";
	private String userid="";
	private String opens="";
	private String pages="";
	private String rows="";
	private String sqlcnt="";
	private List<Map<String,String>> list01=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list02=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list03=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list04=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list05=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list06=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list07=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list08=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list09=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> list10=new ArrayList<Map<String,String>>();

	
	
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



	public List<Map<String, String>> getList01() {
		return list01;
	}



	public void setList01(List<Map<String, String>> list01) {
		this.list01 = list01;
	}



	public List<Map<String, String>> getList02() {
		return list02;
	}



	public void setList02(List<Map<String, String>> list02) {
		this.list02 = list02;
	}



	public List<Map<String, String>> getList03() {
		return list03;
	}



	public void setList03(List<Map<String, String>> list03) {
		this.list03 = list03;
	}



	public List<Map<String, String>> getList04() {
		return list04;
	}



	public void setList04(List<Map<String, String>> list04) {
		this.list04 = list04;
	}



	public List<Map<String, String>> getList05() {
		return list05;
	}



	public void setList05(List<Map<String, String>> list05) {
		this.list05 = list05;
	}



	public List<Map<String, String>> getList06() {
		return list06;
	}



	public void setList06(List<Map<String, String>> list06) {
		this.list06 = list06;
	}



	public List<Map<String, String>> getList07() {
		return list07;
	}



	public void setList07(List<Map<String, String>> list07) {
		this.list07 = list07;
	}



	public List<Map<String, String>> getList08() {
		return list08;
	}



	public void setList08(List<Map<String, String>> list08) {
		this.list08 = list08;
	}



	public List<Map<String, String>> getList09() {
		return list09;
	}



	public void setList09(List<Map<String, String>> list09) {
		this.list09 = list09;
	}



	public List<Map<String, String>> getList10() {
		return list10;
	}



	public void setList10(List<Map<String, String>> list10) {
		this.list10 = list10;
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
			
			if(list01.size()>0){
				jsarr=JSONArray.fromObject(list01);
				jsonObject2.element("xml01", jsarr);
			}
			if(list02.size()>0){
				jsarr=JSONArray.fromObject(list02);
				jsonObject2.element("xml02", jsarr);
			}
			if(list03.size()>0){
				jsarr=JSONArray.fromObject(list03);
				jsonObject2.element("xml03", jsarr);
			}
			if(list04.size()>0){
				jsarr=JSONArray.fromObject(list04);
				jsonObject2.element("xml04", jsarr);
			}
			if(list05.size()>0){
				jsarr=JSONArray.fromObject(list05);
				jsonObject2.element("xml05", jsarr);
			}
			if(list06.size()>0){
				jsarr=JSONArray.fromObject(list06);
				jsonObject2.element("xml06", jsarr);
			}
			if(list07.size()>0){
				jsarr=JSONArray.fromObject(list07);
				jsonObject2.element("xml07", jsarr);
			}
			if(list08.size()>0){
				jsarr=JSONArray.fromObject(list08);
				jsonObject2.element("xml08", jsarr);
			}
			if(list09.size()>0){
				jsarr=JSONArray.fromObject(list09);
				jsonObject2.element("xml09", jsarr);
			}
			if(list10.size()>0){
				jsarr=JSONArray.fromObject(list10);
				jsonObject2.element("xml10", jsarr);
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
