package com.example.ERP.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Covet on 2018/12/9.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Emp {

    @Id
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Role> roles;
}

