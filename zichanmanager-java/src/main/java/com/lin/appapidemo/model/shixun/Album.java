package com.lin.appapidemo.model.shixun;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
//@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_album")
public class Album {
    @Id
    @Column(name = "bid")
    private int bid;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publishtime")
    private Date publishtime;
    @Column(name = "biannum")
    private String biannum;
    @Column(name = "telephone")
    private Long telephone;
    @Column(name = "boomtime")
    private Date boomtime;

    public Album() {
    }

    public Album(Integer bid, String title, String author, String publisher, Date publishtime, String biannum, Long telephone, Date boomtime) {
        this.bid = bid;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishtime = publishtime;
        this.biannum = biannum;
        this.telephone = telephone;
        this.boomtime = boomtime;
    }



    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getBiannum() {
        return biannum;
    }

    public void setBiannum(String biannum) {
        this.biannum = biannum;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public Date getBoomtime() {
        return boomtime;
    }

    public void setBoomtime(Date boomtime) {
        this.boomtime = boomtime;
    }

    @Override
    public String toString() {
        return "Album{" +
                "bid=" + bid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishtime=" + publishtime +
                ", biannum='" + biannum + '\'' +
                ", telephone=" + telephone +
                ", boomtime=" + boomtime +
                '}';
    }
}
