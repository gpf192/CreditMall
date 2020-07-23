package com.xsdzq.mall.model;

public class PresentCategory {

	private Long id;
	private String name;
	private Boolean flag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "PresentCategory [id=" + id + ", name=" + name + ", flag=" + flag + "]";
	}

}
