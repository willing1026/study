package com.aws.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:aws.properties"})
public class S3Application {

    public static void main(String[] args) {
        SpringApplication.run(S3Application.class, args);
    }
}
