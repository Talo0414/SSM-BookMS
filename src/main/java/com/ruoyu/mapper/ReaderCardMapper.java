package com.ruoyu.mapper;

import com.ruoyu.bean.ReaderCard;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ReaderCardMapper {

    int getIdMatchCount(Map<String,Object> map);

    ReaderCard findReaderByReaderId(@Param("readerId") long readerId);

    int resetPassword(Map<String,Object> map);

    int addReaderCard(Map<String,Object> map);

    String getPassword(@Param("readerId") long readerId);

    int deleteReaderCard(@Param("readerId") long readerId);
}
