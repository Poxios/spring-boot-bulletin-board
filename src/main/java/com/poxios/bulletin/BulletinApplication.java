package com.poxios.bulletin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BulletinApplication {
    public static void main(String[] args) {
        SpringApplication.run(BulletinApplication.class, args);
    }
}
