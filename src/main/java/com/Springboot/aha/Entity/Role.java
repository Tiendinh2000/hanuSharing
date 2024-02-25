package com.Springboot.aha.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name",nullable =false)
    @Enumerated(EnumType.STRING)
    private ERole name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }
}
