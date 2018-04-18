package com.visu.banner.service;

import com.visu.banner.dao.BannerDao;
import com.visu.banner.dao.util.TestUtil;
import com.visu.banner.model.Banner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerServiceTest {

    @Autowired
    private BannerService bannerService;

    @MockBean
    private BannerDao bannerDao;

    @Test
    public void testGetBanners() {
        when(bannerDao.getAll()).thenReturn(Arrays.asList(TestUtil.TEST_BANNER_1, TestUtil.TEST_BANNER_2));
        List<Banner> extracted = bannerService.get(2);

        Assert.assertEquals(2, extracted.size());
        Assert.assertNotEquals(extracted.get(0), extracted.get(1));
    }

    @Test
    public void testGetBanners_countMoreThanSize() {
        when(bannerDao.getAll()).thenReturn(Arrays.asList(TestUtil.TEST_BANNER_1, TestUtil.TEST_BANNER_2));
        List<Banner> extracted = bannerService.get(4);

        Assert.assertEquals(2, extracted.size());
        Assert.assertNotEquals(extracted.get(0), extracted.get(1));
    }

    @Test
    public void testGetBanners_zeroCount() {
        List<Banner> extracted = bannerService.get(0);
        Assert.assertNull(extracted);
    }

    @Test
    public void testGetBanners_negativeCount() {
        List<Banner> extracted = bannerService.get(0);
        Assert.assertNull(extracted);
    }

    @Test
    public void testGetBanners_sizeIsNull() {
        when(bannerDao.getAll()).thenReturn(Collections.emptyList());
        List<Banner> extracted = bannerService.get(4);

        Assert.assertTrue(extracted.isEmpty());
    }

    @Test
    public void testAddBanner() {
        when(bannerDao.add(1)).thenReturn(TestUtil.TEST_BANNER_1);
        Banner banner = bannerService.add(1);

        Assert.assertEquals(1, banner.getWeight());
    }

    @Test
    public void testAddBanner_zeroWeight() {
        Banner banner = bannerService.add(0);
        Assert.assertNull(banner);
    }

    @Test
    public void testAddBanner_negativeWeight() {
        Banner banner = bannerService.add(-1);
        Assert.assertNull(banner);
    }

    @Test
    public void testDeleteBanner() {
        when(bannerDao.delete(100L)).thenReturn(true);
        boolean isDeleted = bannerService.delete(100L);

        Assert.assertEquals(true, isDeleted);
    }

    @Test
    public void testDeleteBanner_bannerNonExistent() {
        when(bannerDao.delete(1000L)).thenReturn(false);
        boolean isDeleted = bannerService.delete(1000L);

        Assert.assertEquals(false, isDeleted);
    }
}
