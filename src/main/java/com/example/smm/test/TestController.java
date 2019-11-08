package com.example.smm.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lidongwei
 * @date 2019-10-30
 **/

@RestController
@RequestMapping
public class  TestController {

    @GetMapping("hi")
    public String he(){
        return "hello";
    }
}
