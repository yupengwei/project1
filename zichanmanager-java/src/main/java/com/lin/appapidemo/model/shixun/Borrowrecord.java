package com.lin.appapidemo.model.shixun;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
//@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_borrowrecord")
public class Borrowrecord {

    @Id
    @Column(name = "bid")
    private int bid;

    @Column(name = "biannum")
    private String biannum;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @Column(name = "supply")
    private String supply;

    @Column(name = "usernumber")
    private String usernumber;

    @Column(name = "borrowTime")
    private Date borrowtime;

    @Column(name = "backTime")
    private Date backtime;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBiannum() {
        return biannum;
    }

    public void setBiannum(String biannum) {
        this.biannum = biannum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }


    public Date getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime) {
        this.borrowtime = borrowtime;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }

    @Override
    public String toString() {
        return "Borrowrecord{" +
                "bid=" + bid +
                ", biannum='" + biannum + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", supply='" + supply + '\'' +
                ", usernumber='" + usernumber + '\'' +
                ", borrowtime=" + borrowtime +
                ", backtime=" + backtime +
                '}';
    }
}
