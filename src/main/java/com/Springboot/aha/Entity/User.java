package com.Springboot.aha.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "username", unique = true)
    @Size(min = 3, message = "username >3 characters")
    private String username;

    @Column(name = "password")
    @Size(min = 3, message = "password >3 characters")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Item> itemList;


    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Role> roles = new ArrayList<>();


}
