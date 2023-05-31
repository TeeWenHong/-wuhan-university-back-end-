package com.qingshuge.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    private int pic_id;
    private int user_id;
    private String picname;
    private String picture_path;
    private String HASH;
    private int price;
    private String book_id;
    private boolean buy_flag;
    private String brief_pic;

    @JsonProperty("HASH")
    private String hash;

    public Image(){

    }
    public String getBrief_pic() {
        return brief_pic;
    }

    public void setBrief_pic(String brief_pic) {
        this.brief_pic = brief_pic;
    }

    public boolean isBuy_flag() {
        return buy_flag;
    }

    public void setBuy_flag(boolean buy_flag) {
        this.buy_flag = buy_flag;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getHASH() {
        return HASH;
    }

    public void setHASH(String HASH) {
        this.HASH = HASH;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public Image(int pic_id, int user_id, String picname, String picture_path, String HASH, int price, String book_id, boolean buy_flag, String brief_pic) {
        this.pic_id = pic_id;
        this.user_id = user_id;
        this.picname = picname;
        this.picture_path = picture_path;
        this.HASH = HASH;
        this.price = price;
        this.book_id = book_id;
        this.buy_flag = buy_flag;
        this.brief_pic = brief_pic;
    }

    @Override
    public String toString() {
        return "Image{" +
                "pic_id=" + pic_id +
                ", user_id=" + user_id +
                ", picname='" + picname + '\'' +
                ", picture_path='" + picture_path + '\'' +
                ", HASH='" + HASH + '\'' +
                ", price='" + price + '\'' +
                ", book_id='" + book_id + '\'' +
                ", buy_flag=" + buy_flag +
                ", brief_pic='" + brief_pic + '\'' +
                '}';
    }
}
