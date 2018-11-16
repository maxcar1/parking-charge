package com.maxcar.base.pojo;

import java.io.Serializable;
import java.util.List;

public class Province  implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    public String getLabel() {
        return name;
    }

    public void setLabel(String label) {
        this.label = name;
    }

    private String label;

    public List<City> getChildren() {
        return children;
    }

    public void setChildren(List<City> children) {
        this.children = children;
    }

    private List<City> children;//临时变量，存放市

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}