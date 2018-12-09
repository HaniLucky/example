package com.example.ERP.vo;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Id;

/**
 * Created by Covet on 2018/12/9.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Dep {
    @Id
    private Integer uuid;
    private String name;
    private String tele;
}
