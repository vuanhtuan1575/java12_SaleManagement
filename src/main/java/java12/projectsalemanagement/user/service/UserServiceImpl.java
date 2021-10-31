package java12.projectsalemanagement.user.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.role.repository.RoleRepository;
import java12.projectsalemanagement.user.dto.AddRoleDto;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.repository.UserRepository;
import java12.projectsalemanagement.user.util.UserStatus;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;
	private PasswordEncoder encoder;
	private RoleRepository roleRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.repository = userRepository;
		this.encoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public Object createUser(CreateUserDto dto) {
		// check username exist and email
		Optional<User> optUsername = repository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());
		
		if (optUsername.isPresent()) {
			Map<String, Object> resError = new HashMap<>();
			resError.put("statusCode", "API5557");
			resError.put("message", "Username or email are exist");
			resError.put("result", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resError);
		}
		
		//Find Role
		Optional<Role> optRole = roleRepository.findByName(dto.getRoleName());
		
		if (optRole.isEmpty()) {
			Map<String, Object> resError = new HashMap<>();
			resError.put("statusCode", "API5557");
			resError.put("message", "Role is not exist");
			resError.put("result", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resError);
		}
		
		Set<Role> roles = new HashSet<>();
		roles.add(optRole.get());
		
		User newUser = new User();
		newUser.setUsername(dto.getUsername());
		newUser.setEmail(dto.getEmail());
		newUser.setPassword(encoder.encode(dto.getPassword()));
		newUser.setStatus(UserStatus.ACTIVE);
		newUser.setRoles(roles);

		User saveUser = repository.save(newUser);
		
		return ResponseHandler.getResponse(saveUser, HttpStatus.OK);
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

	@Override
	public Object deleteUser(long id) {
		Optional<User> opUser = repository.findById(id);
		if(opUser.isEmpty()) {
			return ResponseHandler.getErrors("User not exist", HttpStatus.NOT_FOUND);
		}
		repository.delete(opUser.get());
		return ResponseHandler.getResponse("delete success", HttpStatus.OK);
		
	}

	@Override
	public Object findUserById(long id) {
		Optional<User> opUser = repository.findById(id);
		if(opUser.isEmpty()) {
			return ResponseHandler.getErrors("User not exist", HttpStatus.NOT_FOUND);
		}
		return ResponseHandler.getResponse(opUser.get(), HttpStatus.OK);
	}
}
