package com.qingshuge.bean;

public class Msg {

    private int msg_id;
    private int user_id;
    private int to_user_id;
    private int pic_id;
    private String msg_info;
    private boolean confirm;
    private boolean show_flag;
    private int id;
    private String username;
    private String picname;
    private String picture_path;
    private String HASH;
    private String price;
    private String book_id;
    private boolean buy_flag;
    private int money;


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public boolean isBuy_flag() {
        return buy_flag;
    }

    public void setBuy_flag(boolean buy_flag) {
        this.buy_flag = buy_flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isShow_flag() {
        return show_flag;
    }

    public void setShow_flag(boolean show_flag) {
        this.show_flag = show_flag;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public String getMsg_info() {
        return msg_info;
    }

    public void setMsg_info(String msg_info) {
        this.msg_info = msg_info;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
    public Msg(){

    }

    public Msg(int msg_id, int user_id, int to_user_id, int pic_id, String msg_info, boolean confirm, boolean show_flag, int id, String username, String picname, String picture_path, String HASH, String price, String book_id, boolean buy_flag, int money) {
        this.msg_id = msg_id;
        this.user_id = user_id;
        this.to_user_id = to_user_id;
        this.pic_id = pic_id;
        this.msg_info = msg_info;
        this.confirm = confirm;
        this.show_flag = show_flag;
        this.id = id;
        this.username = username;
        this.picname = picname;
        this.picture_path = picture_path;
        this.HASH = HASH;
        this.price = price;
        this.book_id = book_id;
        this.buy_flag = buy_flag;
        this.money = money;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg_id=" + msg_id +
                ", user_id=" + user_id +
                ", to_user_id=" + to_user_id +
                ", pic_id=" + pic_id +
                ", msg_info='" + msg_info + '\'' +
                ", confirm=" + confirm +
                ", show_flag=" + show_flag +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", picname='" + picname + '\'' +
                ", picture_path='" + picture_path + '\'' +
                ", HASH='" + HASH + '\'' +
                ", price='" + price + '\'' +
                ", book_id='" + book_id + '\'' +
                ", buy_flag=" + buy_flag +
                ", money=" + money +
                '}';
    }
}
