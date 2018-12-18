package com.example.ERP.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
