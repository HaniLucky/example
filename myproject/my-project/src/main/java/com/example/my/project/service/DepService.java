package com.example.my.project.service;

import com.example.my.project.vo.Dep;

import java.util.List;

/**
 * Created by Covet on 2018/12/5.
 */
public interface DepService {
    List<Dep> queryDepList(String name, String tele);
}
