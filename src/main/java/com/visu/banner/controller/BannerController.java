package com.visu.banner.controller;

import com.visu.banner.model.Banner;
import com.visu.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {

    private static final String COUNT_SHOULD_BE_POSITIVE = "Count should be positive";
    private static final String WEIGHT_SHOULD_BE_POSITIVE = "Weight should be positive";
    private static final String BANNER_WITH_ID_NOT_EXIST = "Banner with specified id does not exist";

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/banners", method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestParam(value = "count") int count) {
        if (count <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(COUNT_SHOULD_BE_POSITIVE);
        }

        List<Banner> banners = bannerService.get(count);
        return ResponseEntity.ok(banners);
    }

    @RequestMapping(value = "/banners", method = RequestMethod.POST)
    public ResponseEntity add(@RequestParam(value = "weight") int weight ) {
        if (weight <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WEIGHT_SHOULD_BE_POSITIVE);
        }

        Banner banner = bannerService.add(weight);
        return ResponseEntity.ok(banner);
    }

    @RequestMapping(value = "/banners/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id ) {
        boolean result = bannerService.delete(id);
        return result ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BANNER_WITH_ID_NOT_EXIST);
    }
}
