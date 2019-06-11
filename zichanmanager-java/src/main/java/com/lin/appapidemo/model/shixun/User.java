package com.lin.appapidemo.model.shixun;

import javax.persistence.Table;


@Table(name = "t_user")
public class User {
    private int id;
    private String usernumber;
    private String username;
    private String dept;
    private String position;
    private Long  telephone;

    public User() {
    }

    public User(int id, String usernumber, String username, String dept, String position, Long telephone) {
        this.id = id;
        this.usernumber = usernumber;
        this.username = username;
        this.dept = dept;
        this.position = position;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", usernumber='" + usernumber + '\'' +
                ", username='" + username + '\'' +
                ", dept='" + dept + '\'' +
                ", position='" + position + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}
