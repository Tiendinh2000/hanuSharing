package com.Springboot.aha.Entity;


import lombok.*;

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

    @Enumerated(EnumType.STRING)
    private ERole name;

    public static Role getUserRole(){
        return new Role(2,ERole.ROLE_USER);
    }

    public static Role getAdminRole(){
        return new Role(1,ERole.ROLE_ADMIN);
    }
}
