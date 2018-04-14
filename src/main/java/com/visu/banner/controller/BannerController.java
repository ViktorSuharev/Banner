package com.visu.banner.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class BannerController {



    @RequestMapping
    public Response get(@RequestParam("count") int count ) {
        return null;
    }

}
