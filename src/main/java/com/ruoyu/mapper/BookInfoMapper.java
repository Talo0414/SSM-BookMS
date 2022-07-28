package com.ruoyu.mapper;

import com.ruoyu.bean.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoMapper {

    int matchBook(@Param("search") String searchWord);

    List<BookInfo> queryBook(@Param("search") String searchWord);

    List<BookInfo> queryAllBook();

    int addBook(BookInfo bookInfo);

    BookInfo getBook(@Param("bookId") long bookId);

    int editBook(BookInfo bookInfo);

    int deleteBook(@Param("bookId") long bookId);
}
