package com.doug.tutorial.jwtdemo.core.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationUser implements AbstractEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @NotNull(message = "username is mandatory!")
    @Column(nullable = false)
    private String username;

    @ToString.Exclude
    @NotNull(message = "pasword is mandatory!")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "role is mandatory!")
    @Column(nullable = false)
    @Builder.Default
    private String role ="USER";

    public ApplicationUser(@NotNull ApplicationUser applicationUser){
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();

    }
}