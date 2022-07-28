package com.ruoyu.service;

import com.ruoyu.bean.ReaderCard;

import java.util.Map;

public interface ReaderCardService {
    int getIdMatchCount(Map<String,Object> map);

    ReaderCard findReaderByReaderId(long readerId);

    int resetPassword(Map<String,Object> map);

    int addReaderCard(Map<String,Object> map);

    String getPassword(long readerId);

    int deleteReaderCard(long readerId);
}
