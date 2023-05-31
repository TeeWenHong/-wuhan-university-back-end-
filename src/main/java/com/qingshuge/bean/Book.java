package com.qingshuge.bean;

public class Book {
    private int book_id;

    private int user_id;

    private int pic_id;

    private String bookname;

    private String book_path;

    private String brief_book;

    private String tag;

    private String fileType;


    public String getBrief_book() {
        return brief_book;
    }

    public void setBrief_book(String brief_book) {
        this.brief_book = brief_book;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }




    public Book(){

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

    public Book(int book_id, int user_id, int pic_id, String bookname, String book_path, String brief_book, String tag, String fileType) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.pic_id = pic_id;
        this.bookname = bookname;
        this.book_path = book_path;
        this.brief_book = brief_book;
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
                ", brief_book='" + brief_book + '\'' +
                ", tag='" + tag + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
