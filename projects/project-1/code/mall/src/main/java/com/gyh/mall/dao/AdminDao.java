package com.gyh.mall.dao;

import com.gyh.mall.model.Admin;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin);

    List<Admin> allAdmins();
}