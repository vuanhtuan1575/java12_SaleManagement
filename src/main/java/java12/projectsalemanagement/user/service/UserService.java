package java12.projectsalemanagement.user.service;

import java.util.List;

import java12.projectsalemanagement.user.dto.AddRoleDto;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;


public interface UserService {
    Object createUser(CreateUserDto dto);

    List<User> findAll();

    User addRole(AddRoleDto dto);
    Object deleteUser(long id);
    Object findUserById(long id);
}
