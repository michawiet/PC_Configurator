package com.pcc.pc_configurator.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class API {
    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }
}
