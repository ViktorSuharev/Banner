package com.visu.banner.controller;

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

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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
    public void testGet() throws Exception {
        Banner banner = new Banner(1L, 1);
        given(bannerService.get(1)).willReturn(Collections.singletonList(banner));

        MvcResult result = mvc.perform(get("/banners/get")
                .param("count", "1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"weight\": 1\n" +
                "    }\n" +
                "]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetEmpty() throws Exception {
        given(bannerService.get(1)).willReturn(Collections.emptyList());

        mvc.perform(get("/banners/get")
                .param("count", "1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void testAddSuccessful() throws Exception {
        Banner banner = new Banner();
        banner.setWeight(1);
        given(bannerService.add(banner)).willReturn(true);

        MvcResult result = getMvcAddResult();

        Assert.assertEquals("successful", result.getResponse().getContentAsString());
    }

    @Test
    public void testAddFailed() throws Exception {
        Banner banner = new Banner();
        banner.setWeight(1);
        given(bannerService.add(banner)).willReturn(false);

        MvcResult result = getMvcAddResult();

        Assert.assertEquals("failed", result.getResponse().getContentAsString());
    }

    private MvcResult getMvcAddResult() throws Exception {
        return mvc.perform(post("/banners")
                .content("{\n" +
                        "    \"weight\" : 1\n" +
                        "}")
                .characterEncoding("utf-8")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
