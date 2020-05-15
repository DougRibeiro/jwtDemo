package com.doug.tutorial.jwtdemo.endpoint.model;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.EnableAsync;

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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field Title is mandatory")
    @Column(nullable = false)
    private String title;
}
