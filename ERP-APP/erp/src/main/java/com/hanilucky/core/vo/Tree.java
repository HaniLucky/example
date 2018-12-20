package com.hanilucky.core.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 组装树形菜单
 * Created by Covet on 2018/12/17.
 */
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class Tree {
    private String id;
    private String text;
    private boolean checked;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Tree> children;

    // 第一次访问属性的时候不会为null;
    public List<Tree> getChildren(){
        if (children == null){
            children =  new ArrayList<>();
        }
        return children;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}
    
    
}
