package com.visu.banner.service;

import com.visu.banner.model.Banner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BannerServiceImpl implements BannerService {

    private final List<Banner> banners = new ArrayList<>();

    private final AtomicLong idCounter = new AtomicLong(1L);

    @Override
    public List<Banner> get(int count) {
        if (banners.isEmpty()) {
            return banners;
        }

        List<Banner> extractedBanners = new ArrayList<>();
        int weight = getSumWeight();
        for (int i = 0; i < count; ++i) {
            int intLength = weight - getSumWeight(extractedBanners);
            if (intLength > 0) {
                Banner banner = resolveBanner(extractedBanners, intLength);
                extractedBanners.add(banner);
            }
        }

        return extractedBanners;
    }

    @Override
    public boolean add(Banner banner) {
        banner.setId(idCounter.getAndIncrement());
        return banners.add(banner);
    }

    private Banner resolveBanner(List<Banner> extracted, int intLength) {
        int randomNum = getRandomNum(intLength);
        int thresholdWeigh = 0;
        for (Banner banner : banners) {
            if (!extracted.contains(banner)) {
                thresholdWeigh = thresholdWeigh + banner.getWeight();
                if (thresholdWeigh > randomNum) {
                    return banner;
                }
            }
        }

        return null;
    }

    private int getRandomNum(int length) {
        Random rand = new Random();
        return rand.nextInt(length);
    }

    private int getSumWeight() {
        return getSumWeight(banners);
    }

    private int getSumWeight(List<Banner> banners) {
        return banners.stream().mapToInt(Banner::getWeight).sum();
    }

}
