package com.visu.banner.controller;

import com.visu.banner.controller.dto.BannerDto;
import com.visu.banner.model.Banner;
import com.visu.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/banners", method = RequestMethod.GET)
    public ResponseEntity<List<Banner>> get(@RequestParam(value = "count") int count) {
        List<Banner> banners = bannerService.get(count);
        return banners.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(banners);
    }

    @RequestMapping(value = "/banners", method = RequestMethod.POST)
    public ResponseEntity add(@RequestParam(value = "weight") int weight ) {
        Banner banner = bannerService.add(weight);
        return banner != null ? ResponseEntity.ok(new BannerDto(banner)) : ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/banners/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id ) {
        boolean result = bannerService.delete(id);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
