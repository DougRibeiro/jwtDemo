package com.doug.tutorial.jwtdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class AuthApplicationTest {

    @Test
    public void contextLoads(){

    }

    @Test
    public void test(){
        System.out.println(new BCryptPasswordEncoder().encode("Test123"));

        //INSERT INTO `application_user`(password,username,role) VALUES ('$2a$10$AOYnSZjpDG3wOcXzkjFIMuYrEqXPHFFyqBsD4.yS1zNIFsiTtxdDu','Douglas','ADMIN');
        //
        //commit;
    }

}
