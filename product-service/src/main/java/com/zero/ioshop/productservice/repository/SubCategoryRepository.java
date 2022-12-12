package com.zero.ioshop.productservice.repository;

import com.zero.ioshop.productservice.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Modifying
    @Query("update SubCategory s set s.isActive = false where s.id = :id")
    Integer deactivateById(@Param(value = "id") Long id);
}
