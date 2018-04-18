package com.visu.banner.service;

import com.visu.banner.model.Banner;

import java.util.List;

public interface BannerService {

    /**
     * Gets count banners according to following strategy:
     * the change of getting banner is in direct ratio of its weight param
     * @param count
     * @return list of {@link Banner}
     */
    List<Banner> get(int count);

    /**
     * Adds banner
     * @param weight
     * @return {@link Banner} has been added
     */
    Banner add(int weight);

    /**
     * Deletes banner with specified id
     * @param id
     * @return boolean indicator was operation successful or not
     */
    boolean delete(long id);
}
