package com.zero.ioshop.productservice.repository;

import com.zero.ioshop.productservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Query("update Category c set c.isActive = false where c.id = :id")
    Integer deactivateById(@Param(value = "id") Long id);
    List<Category> findByParentId(Long id);
}
