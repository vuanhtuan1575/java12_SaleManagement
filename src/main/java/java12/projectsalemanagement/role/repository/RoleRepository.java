package java12.projectsalemanagement.role.repository;

import java12.projectsalemanagement.role.entity.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	
	public Optional<Role> findByName(String name);

}
