package com.ruoyu.service;

import com.ruoyu.bean.ReaderInfo;

import java.util.List;

public interface ReaderInfoService {
    List<ReaderInfo> getAllReaderInfo();

    ReaderInfo findReaderInfoByReaderId(long readerId);

    int deleteReaderInfo(long readerId);

    int editReaderInfo(ReaderInfo readerInfo);

    int editReaderCard(ReaderInfo readerInfo);

    long addReaderInfo(ReaderInfo readerInfo);
}
