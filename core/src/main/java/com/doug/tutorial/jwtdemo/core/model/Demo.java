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
public class Demo implements AbstractEntity {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @NotNull(message = "title is mandatory!")
    @Column(nullable = false)
    private String title;
}
