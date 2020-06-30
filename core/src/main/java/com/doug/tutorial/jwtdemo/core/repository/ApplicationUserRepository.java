package com.doug.tutorial.jwtdemo.core.repository;

import com.doug.tutorial.jwtdemo.core.model.ApplicationUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser,Long> {

    Optional<ApplicationUser> findByUsername(String username);
}
