package com.ruoyu.mapper;

import com.ruoyu.bean.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReaderInfoMapper {
    List<ReaderInfo> getAllReaderInfo();

    ReaderInfo findReaderInfoByReaderId(@Param("readerId") long readerId);

    int deleteReaderInfo(@Param("readerId")long readerId);

    int editReaderInfo(ReaderInfo readerInfo);

    int editReaderCard(ReaderInfo readerInfo);

    long addReaderInfo(ReaderInfo readerInfo);
}
