package com.zero.ioshop.accountservice.repository;

import com.zero.ioshop.accountservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * this class is used to create repository of Role entity. used to do actions in database
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
