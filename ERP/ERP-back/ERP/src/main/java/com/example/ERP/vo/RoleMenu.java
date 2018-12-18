package com.example.ERP.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

/**
 * Created by Covet on 2018/12/18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleMenu {

    @Id
    private  String roleuuid;

    private  String menuuuid ;
}
