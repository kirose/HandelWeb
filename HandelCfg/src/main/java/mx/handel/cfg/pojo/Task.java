package mx.handel.cfg.pojo;

import java.io.Serializable;

public class Task implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String className;
	private Boolean background;
	private Object data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Boolean getBackground() {
		return background;
	}
	public void setBackground(Boolean background) {
		this.background = background;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
