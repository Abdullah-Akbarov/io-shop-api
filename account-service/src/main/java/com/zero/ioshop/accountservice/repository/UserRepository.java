package com.zero.ioshop.accountservice.repository;

import com.zero.ioshop.accountservice.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * this class is used to create repository of User entity. used to do actions in database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    List<User> findAllByAndIsActiveIsTrue(Sort sort);

    List<User> findAllByAndIsActiveIsTrue(Pageable pageable);

    @Modifying
    @Query("update users u set u.isActive = false where u.id = :id")
    Integer deactivateById(@Param(value = "id") Long id);
    @Query("select u from users u where u.firstName like %:keyword% or u.lastName like %:keyword% or u.username like %:keyword% or u.email like %:keyword% and u.isActive=true")
    List<User> findAllByKeyword(@Param("keyword") String keyword);
}
