package com.visu.banner.dao;

import com.visu.banner.model.Banner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BannerDaoImpl implements BannerDao {

    private static final String GET_ALL_ELEMENTS_QUERY = "select e from Banner e";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Banner add(int weight) {
        Banner banner = new Banner(weight);
        entityManager.persist(banner);
        entityManager.flush();
        return banner;
    }

    @Override
    public boolean delete(long id) {
        Banner banner = getById(id);
        if (banner != null) {
            entityManager.remove(banner);
            return true;
        }

        return false;
    }

    @Override
    public Banner getById(long id) {
        return entityManager.find(Banner.class, id);
    }

    @Override
    public List<Banner> getAll() {
        return entityManager.createQuery(GET_ALL_ELEMENTS_QUERY, Banner.class).getResultList();
    }
}
