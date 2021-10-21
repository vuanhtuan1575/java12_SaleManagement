package java12.projectsalemanagement.user.service;

import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.role.repository.RoleRepository;
import java12.projectsalemanagement.user.dto.AddRoleDto;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.repository.UserRepository;
import java12.projectsalemanagement.user.util.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        repository = userRepository;
        encoder = passwordEncoder;
        this.roleRepository=roleRepository;
    }
    @Override
    public User createUser(CreateUserDto dto) {
        User newUser= new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setStatus(UserStatus.ACTIVE);

        return repository.save(newUser);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User addRole(AddRoleDto dto) {
        Role role = roleRepository.getById(dto.getRoleId());
        User user = repository.getById(dto.getUserId());

        user.addRole(role);

        return repository.save(user);
    }
}
