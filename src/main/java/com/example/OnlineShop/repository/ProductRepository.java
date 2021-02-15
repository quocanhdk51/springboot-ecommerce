package com.example.OnlineShop.repository;

import com.example.OnlineShop.models.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT count(p) > 0 FROM Product p WHERE p.title like :title")
    boolean existsByTitle(@Param("title") String title);
}
