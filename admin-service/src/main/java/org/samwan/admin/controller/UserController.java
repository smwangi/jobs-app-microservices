package org.samwan.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    //@Autowired
    //private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String findAll() {
        LOGGER.info("Employee find");
        return "Users Controller";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello from admin";
    }
}
