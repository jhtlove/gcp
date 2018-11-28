package gcp.springmvc.bean;

import java.util.List;

public class XtBean {
	private String xtbm;
	private String text;
	private String state;
	private List<MkBean> children;
	
	public String getXtbm() {
		return xtbm;
	}
	public void setXtbm(String xtbm) {
		this.xtbm = xtbm;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<MkBean> getChildren() {
		return children;
	}
	public void setChildren(List<MkBean> children) {
		this.children = children;
	}
	
}
