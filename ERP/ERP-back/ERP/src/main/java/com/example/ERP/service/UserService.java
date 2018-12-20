package com.example.ERP.service;

import java.util.Set;

/**
 * Created by Covet on 2018/12/19.
 */
public interface UserService {
    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
