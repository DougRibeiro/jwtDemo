package com.doug.tutorial.jwtdemo.core.repository;

import com.doug.tutorial.jwtdemo.core.model.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JwtRepository extends PagingAndSortingRepository<Demo,Long> {
}
