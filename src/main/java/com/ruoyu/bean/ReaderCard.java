package com.ruoyu.bean;

public class ReaderCard {
    private long readerId;
    private String username;
    private String password;

    public ReaderCard() {
    }

    public ReaderCard(long readerId, String username, String password) {
        this.readerId = readerId;
        this.username = username;
        this.password = password;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ReaderCard{" +
                "readerId=" + readerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
