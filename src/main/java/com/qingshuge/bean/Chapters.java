package com.qingshuge.bean;

public class Chapters {

    private int cpt_id;
    private int book_id;
    private int chapters;
    private String title;
    private String book_path;


    public Chapters(){}
    public int getCpt_id() {
        return cpt_id;
    }

    public void setCpt_id(int cpt_id) {
        this.cpt_id = cpt_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    public String getBook_path() {
        return book_path;
    }

    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }

    public Chapters(int cpt_id, int book_id, int chapters, String title, String book_path) {
        this.cpt_id = cpt_id;
        this.book_id = book_id;
        this.chapters = chapters;
        this.title = title;
        this.book_path = book_path;
    }

    @Override
    public String toString() {
        return "Chapters{" +
                "cpt_id=" + cpt_id +
                ", book_id=" + book_id +
                ", chapters=" + chapters +
                ", title='" + title + '\'' +
                ", book_path='" + book_path + '\'' +
                '}';
    }
}
