package com.lin.appapidemo.model.shixun;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.util.Date;

@Data
//@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_state")
public class State{

    private  int bid;
    private  String biannum;
    private  String name;
    private  Long telephone;
    private  String supply;
    private  String place;
    private  Date buytime;
    private  Date boomtime;

    public State() {
    }

    public State(int bid, String biannum, String name, Long telephone, String supply, String place, Date buytime, Date boomtime) {
        this.bid = bid;
        this.biannum = biannum;
        this.name = name;
        this.telephone = telephone;
        this.supply = supply;
        this.place = place;
        this.buytime = buytime;
        this.boomtime = boomtime;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getBiannum() {
        return biannum;
    }

    public void setBiannum(String biannum) {
        this.biannum = biannum;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
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


    public Date getBuytime() {
        return buytime;
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

    @Override
    public String toString() {
        return "State{" +
                "bid=" + bid +
                ", biannum='" + biannum + '\'' +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                ", supply='" + supply + '\'' +
                ", place='" + place + '\'' +
                ", buytime=" + buytime +
                ", boomtime=" + boomtime +
                '}';
    }

}


