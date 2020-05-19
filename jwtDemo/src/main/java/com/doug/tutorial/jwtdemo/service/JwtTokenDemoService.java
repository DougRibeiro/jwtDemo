package com.doug.tutorial.jwtdemo.service;

import com.doug.tutorial.jwtdemo.core.model.Demo;
import com.doug.tutorial.jwtdemo.core.repository.JwtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtTokenDemoService {
    public final JwtRepository repository;

    public Iterable<Demo> list(Pageable pageable){
        log.info("Listing all demos");
        return repository.findAll(pageable);
    }
}
