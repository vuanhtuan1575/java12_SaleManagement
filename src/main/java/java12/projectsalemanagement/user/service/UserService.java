package java12.projectsalemanagement.user.service;

import java12.projectsalemanagement.user.dto.AddRoleDto;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(CreateUserDto dto);

    List<User> findAll();

    User addRole(AddRoleDto dto);
}
