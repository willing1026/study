package com.openapi.doc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocController {

    @PostMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @PostMapping(value = "/name", consumes = {"application/json"}, produces = {"application/text", "application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String hello(@RequestBody String name) {
        String result = "my name is " + name;
        return result;
    }
}
