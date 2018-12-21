package com.hanilucky.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanilucky.core.vo.Goods;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController<Goods>{


}
