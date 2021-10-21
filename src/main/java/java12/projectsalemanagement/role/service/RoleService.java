package java12.projectsalemanagement.role.service;

import java12.projectsalemanagement.role.dto.CreateRoleDto;
import java12.projectsalemanagement.role.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAll();

    Role createRole(CreateRoleDto dto);
}
