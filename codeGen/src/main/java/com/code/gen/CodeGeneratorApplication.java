package com.code.gen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.io.IOException;

@SpringBootApplication
public class CodeGeneratorApplication {

    @Autowired
    CodeGenService codeGenService;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CodeGeneratorApplication.class, args);

        new CodeGeneratorApplication().run();
    }

    public void run() throws IOException {
        codeGenService.generate();
    }
}
