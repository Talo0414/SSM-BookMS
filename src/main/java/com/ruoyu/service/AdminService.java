package com.ruoyu.service;

import com.ruoyu.bean.Admin;


import java.util.Map;
public interface AdminService {

    Admin getAdmin(long adminID);

    int getMatchCount(Map<String,Object> map);

    int resetPassword(Map<String,Object> map);

    String getPassword(long adminid);

    String getUsername(long adminid);

}
