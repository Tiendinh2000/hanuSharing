package com.Springboot.aha.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "user", indexes = {
        @Index(name = "idx_user_username_unq", columnList = "username", unique = true)
})
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "username", unique = true)
    @Size(min = 3, max = 20, message = "username > 2 characters or < 21 characters")
    private String username;

    @Column(name = "password")
   // @Size(min = 3, max =20, message = "password > 2 characters or < 21 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Item> itemList;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Role> roles = new ArrayList<>();

}
