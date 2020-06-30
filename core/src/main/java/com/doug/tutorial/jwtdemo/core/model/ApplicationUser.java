package com.doug.tutorial.jwtdemo.core.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApplicationUser implements AbstractEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @NotNull(message = "username is mandatory!")
    @Column(nullable = false)
    private String username;

    @NotNull(message = "pasword is mandatory!")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "role is mandatory!")
    @Column(nullable = false)
    private String role ="USER";

    public ApplicationUser(@NotNull ApplicationUser applicationUser){
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();

    }
}