package com.example.OnlineShop.repository;

import com.example.OnlineShop.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findTopByOrderByIdDesc();
}
