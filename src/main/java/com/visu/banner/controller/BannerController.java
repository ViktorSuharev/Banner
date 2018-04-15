package com.visu.banner.controller;

import com.visu.banner.model.Banner;
import com.visu.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {

    private static final String SUCCESSFUL_STATUS = "successful";
    private static final String FAILED_STATUS = "failed";

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/banners/get", method = RequestMethod.GET)
    public ResponseEntity<List<Banner>> get(@RequestParam(value = "count") int count ) {
        List<Banner> banners = bannerService.get(count);
        return banners.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(banners);
    }

    @RequestMapping(value = "/banners", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Banner banner) {
        String status = bannerService.add(banner) ? SUCCESSFUL_STATUS : FAILED_STATUS;
        return ResponseEntity.ok(status);
    }
}
