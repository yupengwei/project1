package com.lin.appapidemo.model.shixun;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Table(name = "t_boom")
public class Commit {
    private int id;
    private String biannum;
    private String name;
    private String word;

    public Commit() {
    }

    public Commit(String biannum, String name, String word) {
        this.biannum = biannum;
        this.name = name;
        this.word = word;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBiannum() {
        return biannum;
    }

    public void setBiannum(String biannum) {
        this.biannum = biannum;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id=" + id +
                ", biannum='" + biannum + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}
