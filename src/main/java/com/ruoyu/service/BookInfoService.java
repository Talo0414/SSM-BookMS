package com.ruoyu.service;

import com.ruoyu.bean.BookInfo;

import java.util.List;

public interface BookInfoService {
    int matchBook(String searchWord);

    List<BookInfo> queryBook(String searchWord);

    List<BookInfo> queryAllBook();

    int addBook(BookInfo bookInfo);

    BookInfo getBook(long bookId);

    int editBook(BookInfo bookInfo);

    int deleteBook(long bookId);
}
