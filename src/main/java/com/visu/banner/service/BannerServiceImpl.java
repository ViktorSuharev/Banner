package com.visu.banner.service;

import com.visu.banner.model.Banner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BannerServiceImpl implements BannerService {

    private final List<Banner> banners = new ArrayList<>();

    private final AtomicLong idCounter = new AtomicLong(1L);

    @Override
    public List<Banner> get(int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }

        if ((banners.size() < count)) {
            return banners;
        }

        int allWeight = getSumWeight();
        int extractedWeight = 0;
        List<Banner> extractedBanners = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            int randomNum = getUniformRandomNum(allWeight - extractedWeight);
            int thresholdWeight = 0;
            for (Banner banner : banners) {
                if (!extractedBanners.contains(banner)) {
                    thresholdWeight = thresholdWeight + banner.getWeight();
                    if (thresholdWeight > randomNum) {
                        extractedBanners.add(banner);
                        extractedWeight = extractedWeight + banner.getWeight();
                        break;
                    }
                }
            }
        }

        return extractedBanners;
    }

    @Override
    public Banner add(int weight) {
        if (weight <= 0) {
            return null;
        }

        Banner banner = new Banner(idCounter.getAndIncrement(), weight);
        banners.add(banner);
        return banner;
    }

    @Override
    public boolean delete(long id) {
        return banners.removeIf(k -> k.getId() == id);
    }

    /**
     * generates random number from [0, bound - 1] according to uniform distribution
     */
    private int getUniformRandomNum(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    private int getSumWeight() {
        return getSumWeight(banners);
    }

    private int getSumWeight(List<Banner> banners) {
        return banners.stream()
                .mapToInt(Banner::getWeight)
                .sum();
    }

}
