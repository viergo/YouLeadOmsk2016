package ru.stairenx.viergo.youleadomsk;

/**
 * Created by viergo on 02.10.16.
 */
public class NewsItem {

    private int server;
    private String imgAuthor;
    private String author;
    private String date;
    private String text;
    private String img;

    public NewsItem(String imgAuthor, String author, String date, String text, String img) {
        this.imgAuthor = imgAuthor;
        this.author = author;
        this.date = date;
        this.text = text;
        this.img = img;
    }

    public NewsItem(int server, String imgAuthor, String author, String date, String text, String img) {
        this.server = server;
        this.imgAuthor = imgAuthor;
        this.author = author;
        this.date = date;
        this.text = text;
        this.img = img;
    }

    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public String getImgAuthor() {
        return imgAuthor;
    }

    public void setImgAuthor(String imgAuthor) {
        this.imgAuthor = imgAuthor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
