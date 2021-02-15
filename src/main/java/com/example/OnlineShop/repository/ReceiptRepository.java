package com.example.OnlineShop.repository;

import com.example.OnlineShop.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Query("SELECT r FROM Receipt r WHERE r.user.id = :user_id")
    List<Receipt> getReceiptByUserID(@Param("user_id") long id);
}
