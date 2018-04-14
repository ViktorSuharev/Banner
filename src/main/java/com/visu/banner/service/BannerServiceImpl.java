package com.visu.banner.service;

import com.visu.banner.model.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BannerServiceImpl implements BannerService {

    private List<Banner> banners = new ArrayList<>();

    @Override
    public List<Banner> get(int count) {
        List<Banner> extractedBanners = new ArrayList<>();
        int sumWeight = getSumWeight();
        int sumExtractedWeight = 0;
        for (int i = 0; i < count; ++i) {
            Banner banner = resolveBanner(extractedBanners, sumWeight, sumExtractedWeight);
            extractedBanners.add(banner);
        }

        return extractedBanners;
    }

    @Override
    public void add(Banner banner) {
        banners.add(banner);
    }

    private Banner resolveBanner(List<Banner> extracted, int sumWeight, int sumExtractedWeight) {
        int intLength = sumWeight - getSumWeight(extracted);
        int randomNum = getRandomNum(intLength);
        return getBanner(randomNum, extracted);
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

    private Banner getBanner(int randomNum, List<Banner> extracted) {
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
}
