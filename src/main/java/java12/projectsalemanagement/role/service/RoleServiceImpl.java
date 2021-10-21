package java12.projectsalemanagement.role.service;

import java12.projectsalemanagement.role.dto.CreateRoleDto;
import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(CreateRoleDto dto) {
        Role newRole= new Role();
        newRole.setName(dto.getName());
        newRole.setDescription(dto.getDescription());
        return roleRepository.save(newRole);
    }
}
