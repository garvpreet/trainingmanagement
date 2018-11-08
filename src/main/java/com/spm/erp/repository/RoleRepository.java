package com.spm.erp.repository;

import java.util.Optional;

import com.spm.erp.model.Role;
import com.spm.erp.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName roleName);
}
