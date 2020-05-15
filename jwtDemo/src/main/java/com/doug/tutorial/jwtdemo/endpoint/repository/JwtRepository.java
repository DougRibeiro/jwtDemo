package com.doug.tutorial.jwtdemo.endpoint.repository;

import com.doug.tutorial.jwtdemo.endpoint.model.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JwtRepository extends PagingAndSortingRepository<Demo,Long> {
}
