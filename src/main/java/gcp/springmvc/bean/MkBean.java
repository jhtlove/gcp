package gcp.springmvc.bean;

import java.util.List;

public class MkBean {
	private String mkbm;
	private String text;
	private String state;
	private List<GnBean> children;
	
	public String getMkbm() {
		return mkbm;
	}
	public void setMkbm(String mkbm) {
		this.mkbm = mkbm;
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
	public List<GnBean> getChildren() {
		return children;
	}
	public void setChildren(List<GnBean> children) {
		this.children = children;
	}
	
	
}
