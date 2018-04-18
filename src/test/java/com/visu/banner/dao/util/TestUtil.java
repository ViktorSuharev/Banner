package com.visu.banner.dao.util;

import com.visu.banner.model.Banner;

public class TestUtil {
    private TestUtil() {}

    public static final String H2_CONNECTION_URL = "jdbc:h2:mem:test;DB_CLOSE_ON_EXIT=FALSE";
    public static final String H2_USER_NAME = "h2";
    public static final String H2_PASSWORD = "h2";

    public static final Banner TEST_BANNER_1 = new Banner(101L, 1);
    public static final Banner TEST_BANNER_2 = new Banner(102L, 2);
    public static final Banner TEST_BANNER_3 = new Banner(103L, 4);
}
