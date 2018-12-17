package com.example.ERP.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by Covet on 2018/12/11.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Goodstype {
    @Id
    private Long uuid;
    private String name;
}
