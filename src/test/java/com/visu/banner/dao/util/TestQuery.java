package com.visu.banner.dao.util;

public class TestQuery {
    private TestQuery() {}

    public static String INSERT_BANNER1_TEST_DATA_QUERY =
            "INSERT INTO banners \n" +
                    "   (id, weight)" +
                    "   VALUES (101, 1)";

    public static String INSERT_BANNER2_TEST_DATA_QUERY =
            "INSERT INTO banners \n" +
                    "   (id, weight)" +
                    "   VALUES (102, 2)";

    public static String INSERT_BANNER3_TEST_DATA_QUERY =
            "INSERT INTO banners \n" +
                    "   (id, weight)" +
                    "   VALUES (103, 4)";

    public static String DELETE_ALL_ROWS =
            "DELETE FROM banners";

}
