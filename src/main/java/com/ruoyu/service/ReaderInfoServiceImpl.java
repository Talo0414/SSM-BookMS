package com.ruoyu.service;

import com.ruoyu.bean.ReaderInfo;
import com.ruoyu.mapper.ReaderInfoMapper;

import java.util.List;

public class ReaderInfoServiceImpl implements ReaderInfoService{
    private ReaderInfoMapper readerInfoMapper;

    public void setReaderInfoMapper(ReaderInfoMapper readerInfoMapper) {
        this.readerInfoMapper = readerInfoMapper;
    }

    @Override
    public List<ReaderInfo> getAllReaderInfo() {
        return readerInfoMapper.getAllReaderInfo();
    }

    @Override
    public ReaderInfo findReaderInfoByReaderId(long readerId) {
        return readerInfoMapper.findReaderInfoByReaderId(readerId);
    }

    @Override
    public int deleteReaderInfo(long readerId) {
        return readerInfoMapper.deleteReaderInfo(readerId);
    }

    @Override
    public int editReaderInfo(ReaderInfo readerInfo) {
        return readerInfoMapper.editReaderInfo(readerInfo);
    }

    @Override
    public int editReaderCard(ReaderInfo readerInfo) {
        return readerInfoMapper.editReaderCard(readerInfo);
    }

    @Override
    public long addReaderInfo(ReaderInfo readerInfo) {
        return readerInfoMapper.addReaderInfo(readerInfo);
    }
}
