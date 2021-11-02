package java12.projectsalemanagement.user.service;

import org.springframework.http.ResponseEntity;

import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.dto.UpdateUserDto;


public interface UserService {
	ResponseEntity<Object> createUser(CreateUserDto dto);

    ResponseEntity<Object> findAll();

    ResponseEntity<Object> deleteUser(long id);
    
    ResponseEntity<Object> findUserById(long id);
    
    ResponseEntity<Object> updateUser(long id, UpdateUserDto dto);
    
    ResponseEntity<Object> deactiveUser(long id);
    ResponseEntity<Object> activeUser(long id);
}
