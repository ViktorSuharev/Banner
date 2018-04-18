package com.visu.banner.controller.dto;

import com.visu.banner.model.Banner;

public class BannerDto {

    private Banner banner;

    public BannerDto(Banner banner) {
        this.banner = banner;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
