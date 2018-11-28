package ihis.busiws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CxOutputBean {
	private boolean state;
	private String error="";
	private int rowcount=0;
	private List<Map<String,String>> row01=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> row02=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> row03=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> row04=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> row05=new ArrayList<Map<String,String>>();
	private List<Map<String,String>> row06=new ArrayList<Map<String,String>>();
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getRowcount() {
		return rowcount;
	}
	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}
	public List<Map<String, String>> getRow01() {
		return row01;
	}
	public void setRow01(List<Map<String, String>> row01) {
		this.row01 = row01;
	}
	public List<Map<String, String>> getRow02() {
		return row02;
	}
	public void setRow02(List<Map<String, String>> row02) {
		this.row02 = row02;
	}
	public List<Map<String, String>> getRow03() {
		return row03;
	}
	public void setRow03(List<Map<String, String>> row03) {
		this.row03 = row03;
	}
	public List<Map<String, String>> getRow04() {
		return row04;
	}
	public void setRow04(List<Map<String, String>> row04) {
		this.row04 = row04;
	}
	public List<Map<String, String>> getRow05() {
		return row05;
	}
	public void setRow05(List<Map<String, String>> row05) {
		this.row05 = row05;
	}
	public List<Map<String, String>> getRow06() {
		return row06;
	}
	public void setRow06(List<Map<String, String>> row06) {
		this.row06 = row06;
	}
	
}
