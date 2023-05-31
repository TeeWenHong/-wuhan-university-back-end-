package com.qingshuge.bean;

public class ShowAllInfo {

    private int id;
    private String username;
    private String password;
    private String email;
    private int money;
    private int fans;

//------------------------------------------------------
    private int book_id;
    private int user_id;
    private int pic_id;
    private String bookname;
    private String book_path;
    private String tag;
    private String fileType;
    private String sex;
    private String phone;
    private String brief;
    private String brief_book;
//----------------------------------------------------------------
    private String picname;
    private String picture_path;
    private String HASH;
    private String price;
    private String brief_pic;

//---------------------------------------------------------------

    private int cpt_id;
//    private int book_id;
    private int chapters;
    private String title;
//    private String book_path;


    public String getBrief_book() {
        return brief_book;
    }

    public void setBrief_book(String brief_book) {
        this.brief_book = brief_book;
    }

    public String getBrief_pic() {
        return brief_pic;
    }

    public void setBrief_pic(String brief_pic) {
        this.brief_pic = brief_pic;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getCpt_id() {
        return cpt_id;
    }

    public void setCpt_id(int cpt_id) {
        this.cpt_id = cpt_id;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ShowAllInfo() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBook_path() {
        return book_path;
    }

    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ShowAllInfo(int id, String username, String password, String email, int money, int fans, int book_id, int user_id, int pic_id, String bookname, String book_path, String tag, String fileType, String sex, String phone, String brief, String brief_book, String picname, String picture_path, String HASH, String price, String brief_pic, int cpt_id, int chapters, String title) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.money = money;
        this.fans = fans;
        this.book_id = book_id;
        this.user_id = user_id;
        this.pic_id = pic_id;
        this.bookname = bookname;
        this.book_path = book_path;
        this.tag = tag;
        this.fileType = fileType;
        this.sex = sex;
        this.phone = phone;
        this.brief = brief;
        this.brief_book = brief_book;
        this.picname = picname;
        this.picture_path = picture_path;
        this.HASH = HASH;
        this.price = price;
        this.brief_pic = brief_pic;
        this.cpt_id = cpt_id;
        this.chapters = chapters;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ShowAllInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", money=" + money +
                ", fans=" + fans +
                ", book_id='" + book_id + '\'' +
                ", user_id=" + user_id +
                ", pic_id=" + pic_id +
                ", bookname='" + bookname + '\'' +
                ", book_path='" + book_path + '\'' +
                ", tag='" + tag + '\'' +
                ", fileType='" + fileType + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", brief='" + brief + '\'' +
                ", brief_book='" + brief_book + '\'' +
                ", picname='" + picname + '\'' +
                ", picture_path='" + picture_path + '\'' +
                ", HASH='" + HASH + '\'' +
                ", price='" + price + '\'' +
                ", brief_pic='" + brief_pic + '\'' +
                ", cpt_id=" + cpt_id +
                ", chapters=" + chapters +
                ", title='" + title + '\'' +
                '}';
    }
}
