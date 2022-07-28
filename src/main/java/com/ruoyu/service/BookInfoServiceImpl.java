package com.ruoyu.service;

import com.ruoyu.bean.BookInfo;
import com.ruoyu.mapper.BookInfoMapper;

import java.util.List;

public class BookInfoServiceImpl implements BookInfoService{
    private BookInfoMapper bookInfoMapper;

    public void setBookInfoMapper(BookInfoMapper bookInfoMapper) {
        this.bookInfoMapper = bookInfoMapper;
    }

    @Override
    public int matchBook(String searchWord) {
        return bookInfoMapper.matchBook(searchWord);
    }

    @Override
    public List<BookInfo> queryBook(String searchWord) {
        return bookInfoMapper.queryBook(searchWord);
    }

    @Override
    public List<BookInfo> queryAllBook() {
        return bookInfoMapper.queryAllBook();
    }

    @Override
    public int addBook(BookInfo bookInfo) {
        return bookInfoMapper.addBook(bookInfo);
    }

    @Override
    public BookInfo getBook(long bookId) {
        return bookInfoMapper.getBook(bookId);
    }

    @Override
    public int editBook(BookInfo bookInfo) {
        return bookInfoMapper.editBook(bookInfo);
    }

    @Override
    public int deleteBook(long bookId) {
        return bookInfoMapper.deleteBook(bookId);
    }
}
