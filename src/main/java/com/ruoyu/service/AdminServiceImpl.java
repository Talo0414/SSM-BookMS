package com.ruoyu.service;

import com.ruoyu.bean.Admin;
import com.ruoyu.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class AdminServiceImpl implements AdminService{
    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin getAdmin(long adminID) {
        return adminMapper.getAdmin(adminID);
    }

    @Override
    public int getMatchCount(Map<String, Object> map) {
        return adminMapper.getMatchCount(map);
    }

    @Override
    public int resetPassword(Map<String, Object> map) {
        return adminMapper.resetPassword(map);
    }

    @Override
    public String getPassword(long adminid) {
        return adminMapper.getPassword(adminid);
    }

    @Override
    public String getUsername(long adminid) {
        return adminMapper.getUsername(adminid);
    }
}
