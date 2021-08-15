package com.Springboot.aha.Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "account")
    private List<ITemEntity> itemList = new ArrayList<ITemEntity>();


    public int getAccount_id() {
        return account_id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ITemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<ITemEntity> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "account_id=" + account_id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
