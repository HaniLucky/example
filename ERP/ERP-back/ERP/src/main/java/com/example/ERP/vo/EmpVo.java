package com.example.ERP.vo;

import lombok.*;

import javax.persistence.Id;
import java.util.Date;

/**
 * emp映射VO 条件映射与返回映射
 * Created by Covet on 2018/12/9.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmpVo extends Emp{

    private Integer uuid;
    private String username;
    private String pwd;
    private String name;
    private String gender;
    private String email;
    private String tele;
    private String address;
    private Date birthday;
    private Long depuuid;

    private String depNm;
    private Date birthdayBegin;
    private Date birthdayEnd;
    private String birthdayBeginStr;
    private String birthdayEndStr;
}
