package com.visu.align.ims.dao;

import com.visu.align.ims.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Access to the product data
 */
public interface ProductDao extends CrudRepository<Product, BigInteger> {

    /**
     * Find products by name
     *
     * @param name name of product
     * @return list of {@link Product}
     * with specified {@link Product#name} param
     */
    @Query("SELECT p FROM Product p WHERE p.name=:name")
    List<Product> getProductsByName(@Param("name") String name);

    /**
     * Find products by brand
     *
     * @param brand brand of product
     * @return list of {@link Product}
     * with specified {@link Product#brand} param
     */
    @Query("SELECT p FROM Product p WHERE p.brand=:brand")
    List<Product> getProductsByBrand(@Param("brand") String brand);

    /**
     * Find products which quantity less than specified
     *
     * @param quantity quantity of product
     * @return list of {@link Product}
     * with {@link Product#quantity} param less than specified
     */
    @Query("SELECT p FROM Product p WHERE p.quantity < :quantity")
    List<Product> getLeftovers(@Param("quantity") int quantity);
}
