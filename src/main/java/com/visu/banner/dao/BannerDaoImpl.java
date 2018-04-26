package com.visu.banner.dao;

import com.visu.banner.model.Banner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BannerDaoImpl implements BannerDao {

    private static final String ID = "id";
    private static final String GET_ALL_ELEMENTS_QUERY = "select e from Banner e";
    private static final String DELETE_BY_ID_QUERY = "delete from Banner where id = :id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Banner add(int weight) {
        Banner banner = new Banner(weight);
        entityManager.persist(banner);
        return banner;
    }

    @Override
    public boolean delete(long id) {
        int result = entityManager.createQuery(DELETE_BY_ID_QUERY)
                .setParameter(ID, id)
                .executeUpdate();
        return result == 1;
    }

    @Override
    public List<Banner> getAll() {
        return entityManager.createQuery(GET_ALL_ELEMENTS_QUERY, Banner.class)
                .getResultList();
    }
}
