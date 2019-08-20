package com.garbage.classification.controller;

import com.garbage.classification.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author domain
 * @date 2019-08-20
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @ApiVersion(1)
    @GetMapping("/{version}/hello")
    public String hello() {
        return "V1 hello";
    }

    @ApiVersion(2)
    @GetMapping("/{version}/hello")
    public String hello2() {
        return "V2 hello";
    }

    @ApiVersion(5)
    @GetMapping("/{version}/hello")
    public String hello5() {
        return "V5 hello";
    }

}
