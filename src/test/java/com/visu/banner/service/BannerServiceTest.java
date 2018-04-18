package com.visu.banner.service;

import com.visu.banner.model.Banner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BannerServiceTest {

    private BannerService bannerService;

    @Before
    public void setUp() {
        bannerService = new BannerServiceImpl();
    }

    @Test
    public void testGetBanners() {
        bannerService.add(1);
        bannerService.add(1);
        bannerService.add(2);
        bannerService.add(4);

        List<Banner> extracted = bannerService.get(2);
        Assert.assertEquals(2, extracted.size());
        Assert.assertNotEquals(extracted.get(0), extracted.get(1));
    }

    @Test
    public void testGetBanners_countMoreThanSize() {
        bannerService.add(1);
        bannerService.add(1);

        List<Banner> extracted = bannerService.get(4);
        Assert.assertEquals(2, extracted.size());
        Assert.assertNotEquals(extracted.get(0), extracted.get(1));
    }

    @Test
    public void testGetBanners_zeroCount() {
        List<Banner> extracted = bannerService.get(0);
        Assert.assertTrue(extracted.isEmpty());
    }

    @Test
    public void testGetBanners_negativeCount() {
        List<Banner> extracted = bannerService.get(0);
        Assert.assertTrue(extracted.isEmpty());
    }

    @Test
    public void testGetBanners_sizeIsNull() {
        List<Banner> extracted = bannerService.get(4);
        Assert.assertTrue(extracted.isEmpty());
    }

    @Test
    public void testAddBanner() {
        Assert.assertEquals(0, bannerService.get(1).size());
        Banner banner = bannerService.add(1);
        Assert.assertNotNull(banner);
        Assert.assertEquals(1, bannerService.get(1).size());
    }

    @Test
    public void testAddBanner_zeroWeight() {
        Assert.assertEquals(0, bannerService.get(1).size());
        Banner banner = bannerService.add(0);
        Assert.assertNull(banner);
        Assert.assertEquals(0, bannerService.get(1).size());
    }

    @Test
    public void testAddBanner_negativeWeight() {
        Assert.assertEquals(0, bannerService.get(1).size());
        Banner banner = bannerService.add(-1);
        Assert.assertNull(banner);
        Assert.assertEquals(0, bannerService.get(1).size());
    }

    @Test
    public void testDeleteBanner() {
        Banner banner = bannerService.add(1);

        Assert.assertEquals(1, bannerService.get(1).size());
        boolean isDeleted = bannerService.delete(banner.getId());
        Assert.assertEquals(true, isDeleted);

        Assert.assertEquals(0, bannerService.get(1).size());
    }

    @Test
    public void testDeleteBanner_bannerNonExistent() {
        bannerService.add(1);

        Assert.assertEquals(1, bannerService.get(1).size());
        boolean isDeleted = bannerService.delete(100L);
        Assert.assertEquals(false, isDeleted);

        Assert.assertEquals(1, bannerService.get(1).size());
    }
}
