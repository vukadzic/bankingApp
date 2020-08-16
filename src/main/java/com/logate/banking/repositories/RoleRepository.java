package com.logate.banking.repositories;

import com.logate.banking.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Override
    Optional<Role> findById(Integer integer);

    @Query("select role from Role role where role.name=?1")
    Optional<Role> findByName(String name);
}
