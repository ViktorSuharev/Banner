package com.visu.banner.dao;

import com.visu.banner.model.Banner;

import java.util.List;

public interface BannerDao {

    Banner add(int weight);

    boolean delete(long id);

    Banner getById(long id);

    List<Banner> getAll();
}
