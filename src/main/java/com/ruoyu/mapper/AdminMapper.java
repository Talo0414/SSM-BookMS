package com.ruoyu.mapper;

import com.ruoyu.bean.Admin;
import org.apache.ibatis.annotations.Param;


import java.util.Map;
public interface AdminMapper {

    Admin getAdmin(@Param("adminId") long adminID);

    int getMatchCount(Map<String,Object> map);

    int resetPassword(Map<String,Object> map);

    String getPassword(@Param("adminId") long adminId);

    String getUsername(@Param("adminId") long adminId);

}
