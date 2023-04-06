package com.qingshuge.bean;

public class Book {
    private String book_id;

    private int user_id;

    private int pic_id;

    private String bookname;

    private String book_path;

    private String tag;

    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }




    public Book(){

    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
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

    public Book(String book_id, int user_id, int pic_id, String bookname, String book_path, String tag, String fileType) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.pic_id = pic_id;
        this.bookname = bookname;
        this.book_path = book_path;
        this.tag = tag;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id='" + book_id + '\'' +
                ", user_id=" + user_id +
                ", pic_id=" + pic_id +
                ", bookname='" + bookname + '\'' +
                ", book_path='" + book_path + '\'' +
                ", tag='" + tag + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
