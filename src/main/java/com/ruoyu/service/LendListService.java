package com.ruoyu.service;

import com.ruoyu.bean.LendList;

import java.util.List;
import java.util.Map;

public interface LendListService {
    int returnBookOne(Map<String,Object> map);

    int returnBookTwo(long bookId);

    int lendBookOne(Map<String,Object> map);

    int lendBookTwo(long bookId);

    List<LendList> lendList();

    List<LendList> myLendList(long bookId);

    int deleteLend(long serNum);
}
