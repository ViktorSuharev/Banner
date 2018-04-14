package com.visu.banner;

import com.visu.banner.model.Banner;
import com.visu.banner.service.BannerService;
import com.visu.banner.service.BannerServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BannerServiceTest {

    @Test
    public void testGetBanners() {
        BannerService bannerService = new BannerServiceImpl();

        Banner banner1 = new Banner(1L, 1);
        Banner banner2 = new Banner(2L, 1);
        Banner banner3 = new Banner(3L, 2);
        Banner banner4 = new Banner(4L, 4);

        bannerService.add(banner1);
        bannerService.add(banner2);
        bannerService.add(banner3);
        bannerService.add(banner4);


        for (int i = 0; i < 10; ++i) {
            List<Banner> extracted = bannerService.get(2);
            System.out.println(extracted);

            Assert.assertEquals(2, extracted.size());
        }
    }
}
