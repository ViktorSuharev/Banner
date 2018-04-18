package com.visu.banner.dao;

import com.visu.banner.dao.util.TestQuery;
import com.visu.banner.dao.util.TestUtil;
import com.visu.banner.model.Banner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerDaoTest {
    private Connection connection;
    private Statement statement;

    @Autowired
    private BannerDao bannerDao;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection(TestUtil.H2_CONNECTION_URL, TestUtil.H2_USER_NAME, TestUtil.H2_PASSWORD);
        statement = connection.createStatement();

        statement.executeUpdate(TestQuery.DELETE_ALL_ROWS);
        statement.executeUpdate(TestQuery.INSERT_BANNER1_TEST_DATA_QUERY);
        statement.executeUpdate(TestQuery.INSERT_BANNER2_TEST_DATA_QUERY);
        statement.executeUpdate(TestQuery.INSERT_BANNER3_TEST_DATA_QUERY);
    }

    @After
    public void tearDown() throws SQLException {
        statement.close();
        connection.close();
    }

    @Test
    public void testGetById() {
        Banner banner = bannerDao.getById(101L);
        Assert.assertNotNull("There is no banners with specified id", banner);

        Assert.assertEquals(TestUtil.TEST_BANNER_1, banner);
    }

    @Test
    public void testGetById_nonExistent() {
        Banner banner = bannerDao.getById(100L);
        Assert.assertNull("There is some banners with specified id", banner);
    }

    @Test
    public void testGetAll() {
        List<Banner> banners = bannerDao.getAll();
        Assert.assertEquals("There are not 3 banners configured", 3, banners.size());
        List<Banner> expectedList = Arrays.asList(TestUtil.TEST_BANNER_1, TestUtil.TEST_BANNER_2, TestUtil.TEST_BANNER_3);
        Assert.assertEquals("There is some banners with specified id", expectedList, banners);
    }

    @Test
    @Transactional
    public void testAdd() {
        int bannerCount = bannerDao.getAll().size();
        bannerDao.add(1);
        Assert.assertEquals("Expected different count", bannerCount + 1, bannerDao.getAll().size());
    }

    @Test
    @Transactional
    public void testDelete() {
        int bannerCount = bannerDao.getAll().size();
        boolean result = bannerDao.delete(103L);
        Assert.assertTrue("Deletion was not successful", result);
        Assert.assertEquals(bannerCount - 1, bannerDao.getAll().size());
    }

    @Test
    @Transactional
    public void testDelete_notExistent() {
        int bannerCount = bannerDao.getAll().size();
        boolean result = bannerDao.delete(100L);
        Assert.assertFalse("Deletion was successful", result);
        Assert.assertEquals(bannerCount, bannerDao.getAll().size());
    }
}
