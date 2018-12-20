package com.example.ERP.service.impl;

import com.example.ERP.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Covet on 2018/12/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
