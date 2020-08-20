package com.logate.banking.repositories;

import com.logate.banking.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {


    @Query("select role from Role role where role.name=?1")
    Role findByName(String name);
}
