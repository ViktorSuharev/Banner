package com.visu.banner.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visu.banner.model.Banner;
import com.visu.banner.service.BannerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BannerController.class)
public class BannerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BannerService bannerService;

    @Test
    public void testGet_countIsOne() throws Exception {
        Banner banner = new Banner(1L, 1);
        given(bannerService.get(1)).willReturn(Collections.singletonList(banner));

        MvcResult result = mvc.perform(get("/banners")
                .param("count", "1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\": 1,\"weight\": 1}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGet_countMoreThanOne() throws Exception {
        Banner banner1 = new Banner(1L, 1);
        Banner banner2 = new Banner(2L, 2);

        given(bannerService.get(2)).willReturn(Arrays.asList(banner1, banner2));

        MvcResult result = mvc.perform(get("/banners")
                .param("count", "2")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<Banner> banners = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Banner>>(){});
        Assert.assertTrue(banners.contains(banner1));
        Assert.assertTrue(banners.contains(banner2));
    }

    @Test
    public void testGet_emptyBannerList() throws Exception {
        given(bannerService.get(1)).willReturn(Collections.emptyList());

        mvc.perform(get("/banners")
                .param("count", "1"))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void testAdd() throws Exception {
        Banner banner = new Banner(1L, 1);
        given(bannerService.add(1)).willReturn(banner);

        MvcResult result = mvc.perform(post("/banners")
                .param("weight", "1"))
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"banner\":{\"id\":1,\"weight\":1}}";
        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void testAdd_notPositiveWeight() throws Exception {
        given(bannerService.add(-1)).willReturn(null);

        mvc.perform(post("/banners")
                .param("weight", "-1"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        given(bannerService.delete(1)).willReturn(true);

        mvc.perform(delete("/banners/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDelete_nonExistent() throws Exception {
        given(bannerService.delete(100)).willReturn(false);

        mvc.perform(delete("/banners/100"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
