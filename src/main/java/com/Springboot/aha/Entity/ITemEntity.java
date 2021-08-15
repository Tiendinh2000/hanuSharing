package com.Springboot.aha.Entity;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ITemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public int getItem_id() {
        return item_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "ITemEntity{" +
                "item_id=" + item_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", account=" + account +
                '}';
    }
}
