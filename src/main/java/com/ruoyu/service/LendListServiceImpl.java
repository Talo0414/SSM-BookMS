package com.ruoyu.service;

import com.ruoyu.bean.LendList;
import com.ruoyu.mapper.LendListMapper;

import java.util.List;
import java.util.Map;

public class LendListServiceImpl implements LendListService{
    private LendListMapper lendListMapper;

    public void setLendListMapper(LendListMapper lendListMapper) {
        this.lendListMapper = lendListMapper;
    }

    @Override
    public int returnBookOne(Map<String, Object> map) {
        return lendListMapper.returnBookOne(map);
    }

    @Override
    public int returnBookTwo(long bookId) {
        return lendListMapper.returnBookTwo(bookId);
    }

    @Override
    public int lendBookOne(Map<String, Object> map) {
        return lendListMapper.lendBookOne(map);
    }

    @Override
    public int lendBookTwo(long bookId) {
        return lendListMapper.lendBookTwo(bookId);
    }

    @Override
    public List<LendList> lendList() {
        return lendListMapper.lendList();
    }

    @Override
    public List<LendList> myLendList(long bookId) {
        return lendListMapper.myLendList(bookId);
    }

    @Override
    public int deleteLend(long serNum) {
        return lendListMapper.deleteLend(serNum);
    }
}
