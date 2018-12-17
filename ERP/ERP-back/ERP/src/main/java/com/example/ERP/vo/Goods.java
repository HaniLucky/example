package com.example.ERP.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by Covet on 2018/12/11.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Goods {

    @Id
    private Integer uuid;
    private String name;
    private String origin;
    private String producer;
    private String unit;
    private BigDecimal inprice;
    private BigDecimal outprice;
    private BigDecimal goodstypeuuid;
}
