package com.lin.appapidemo.model.shixun;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.util.Date;


@Data
@Accessors(chain = true)
@Table(name = "t_fix")
public class Fix {
        private int bid;
        private String biannum;
        private String name;
        private String supply;
        private String place;
        private Long telephone;
        private Date buytime;
        private Date boomtime;

    public Date getBuytime() {
        return buytime;
    }

    public Date getBuytime(Date buytime) {
        return this.buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public Date getBoomtime() {
        return boomtime;
    }

    public void setBoomtime(Date boomtime) {
        this.boomtime = boomtime;
    }

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

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }


    @Override
    public String toString() {
        return "Fix{" +
                "bid=" + bid +
                ", biannum='" + biannum + '\'' +
                ", name='" + name + '\'' +
                ", supply='" + supply + '\'' +
                ", place='" + place + '\'' +
                ", telephone=" + telephone +
                ", buytime=" + buytime +
                ", boomtime=" + boomtime +
                '}';
    }

}

