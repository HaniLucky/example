package com.example.ERP.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class EmpRole {
    @Id
    private Integer empuuid;
    private Integer roleuuid;
}
