package com.ruoyu.bean;

import java.util.Date;

public class LendList {
    private long serNum;
    private long bookId;
    private long readerId;
    private Date lendDate;
    private Date backDate;

    public LendList() {
    }

    public LendList(long serNum, long bookId, long readerId, Date lendDate, Date backDate) {
        this.serNum = serNum;
        this.bookId = bookId;
        this.readerId = readerId;
        this.lendDate = lendDate;
        this.backDate = backDate;
    }

    public long getSerNum() {
        return serNum;
    }

    public void setSerNum(long serNum) {
        this.serNum = serNum;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    @Override
    public String toString() {
        return "LendList{" +
                "serNum=" + serNum +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", lendDate=" + lendDate +
                ", backDate=" + backDate +
                '}';
    }
}
