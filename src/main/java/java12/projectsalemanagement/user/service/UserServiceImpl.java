package java12.projectsalemanagement.user.service;

import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.repository.UserRepository;
import java12.projectsalemanagement.user.util.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        repository = userRepository;
        encoder = passwordEncoder;
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
}
