package com.doug.tutorial.jwtdemo.controller;


import com.doug.tutorial.jwtdemo.core.model.Demo;
import com.doug.tutorial.jwtdemo.service.JwtTokenDemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@RequestMapping("v1/admin/demo")
@RequiredArgsConstructor
public class JWTDemoController {

    public final JwtTokenDemoService jwtTokenDemoService;


    @GetMapping
    public ResponseEntity<Iterable<Demo>> list(Pageable pageable){
        return new ResponseEntity<>(jwtTokenDemoService.list(pageable), HttpStatus.OK);
    }
}