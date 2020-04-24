package com.jyp.kill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.jyp.kill.dao")
@SpringBootApplication
public class KillApplication {

    public static void main(String[] args) {
        SpringApplication.run(KillApplication.class, args);
    }

}
