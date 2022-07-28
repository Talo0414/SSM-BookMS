package com.ruoyu.service;

import com.ruoyu.bean.ReaderCard;
import com.ruoyu.mapper.ReaderCardMapper;

import java.util.Map;

public class ReaderCardServiceImpl implements ReaderCardService{
    private ReaderCardMapper readerCardMapper;

    public void setReaderCardMapper(ReaderCardMapper readerCardMapper) {
        this.readerCardMapper = readerCardMapper;
    }

    @Override
    public int getIdMatchCount(Map<String, Object> map) {
        return readerCardMapper.getIdMatchCount(map);
    }

    @Override
    public ReaderCard findReaderByReaderId(long readerId) {
        return readerCardMapper.findReaderByReaderId(readerId);
    }

    @Override
    public int resetPassword(Map<String, Object> map) {
        return readerCardMapper.resetPassword(map);
    }

    @Override
    public int addReaderCard(Map<String, Object> map) {
        return readerCardMapper.addReaderCard(map);
    }

    @Override
    public String getPassword(long readerId) {
        return readerCardMapper.getPassword(readerId);
    }

    @Override
    public int deleteReaderCard(long readerId) {
        return readerCardMapper.deleteReaderCard(readerId);
    }
}
