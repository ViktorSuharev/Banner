package com.visu.banner.service;

import com.visu.banner.model.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> get(int count);
    void add(Banner banner);

}
