package com.ruoyu.mapper;

import com.ruoyu.bean.LendList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LendListMapper {

    int returnBookOne(Map<String,Object> map);

    int returnBookTwo(@Param("bookId") long bookId);

    int lendBookOne(Map<String,Object> map);

    int lendBookTwo(@Param("bookId")long bookId);

    List<LendList> lendList();

    List<LendList> myLendList(@Param("readerId")long readerId);

    int deleteLend(@Param("serNum") long serNum);
}
