package com.visu.banner.service;

import com.visu.banner.dao.BannerDao;
import com.visu.banner.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<Banner> get(int count) {
        List<Banner> banners = getAll();
        if ((banners.size() < count)) {
            return banners;
        }

        int currentSumWeight = getSumWeight(banners);
        List<Banner> extractedBanners = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            int thresholdWeight = 0;
            int randomNum = getUniformRandomNum(currentSumWeight);
            for (Banner banner : banners) {
                if (extractedBanners.contains(banner)) {
                    continue;
                }

                thresholdWeight = thresholdWeight + banner.getWeight();
                if (thresholdWeight > randomNum) {
                    extractedBanners.add(banner);
                    currentSumWeight = currentSumWeight - banner.getWeight();
                    break;
                }
            }
        }

        return extractedBanners;
    }

    @Override
    @Transactional
    public Banner add(int weight) {
        return bannerDao.add(weight);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        return bannerDao.delete(id);
    }

    @Transactional
    public List<Banner> getAll() {
        return bannerDao.getAll();
    }

    /**
     * generates random number from [0, bound - 1] according to uniform distribution
     */
    private int getUniformRandomNum(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    private int getSumWeight(List<Banner> banners) {
        return banners.stream()
                .mapToInt(Banner::getWeight)
                .sum();
    }

}
