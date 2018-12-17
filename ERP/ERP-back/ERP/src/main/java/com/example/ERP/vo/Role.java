package com.example.ERP.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    private Long uuid;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Menu> menus;
}
