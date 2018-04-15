package com.visu.banner.service;

import com.visu.banner.model.Banner;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BannerServiceTest {

    private final BannerService bannerService = new BannerServiceImpl();

    @Test
    public void testGetBanners() {
        Banner banner1 = new Banner(1L, 1);
        Banner banner2 = new Banner(2L, 1);
        Banner banner3 = new Banner(3L, 2);
        Banner banner4 = new Banner(4L, 4);

        bannerService.add(banner1);
        bannerService.add(banner2);
        bannerService.add(banner3);
        bannerService.add(banner4);

        List<Banner> extracted = bannerService.get(2);
        Assert.assertEquals(2, extracted.size());
        Assert.assertNotEquals(extracted.get(0), extracted.get(1));
    }

    @Test
    public void testGetBanners_countMoreThanSize() {
        Banner banner1 = new Banner(1L, 1);
        Banner banner2 = new Banner(2L, 1);

        bannerService.add(banner1);
        bannerService.add(banner2);

        List<Banner> extracted = bannerService.get(4);
        Assert.assertEquals(2, extracted.size());
        Assert.assertNotEquals(extracted.get(0), extracted.get(1));
    }

    @Test
    public void testGetBanners_sizeIsNull() {
        List<Banner> extracted = bannerService.get(4);
        Assert.assertTrue(extracted.isEmpty());
    }

    @Test
    public void testAddBanners() {
        Banner banner = new Banner();
        banner.setWeight(1);

        boolean status = bannerService.add(banner);
        Assert.assertTrue(status);
    }
}
