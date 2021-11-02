package java12.projectsalemanagement.user.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.common.util.DateUtils;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.role.repository.RoleRepository;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.dto.UpdateUserDto;
import java12.projectsalemanagement.user.dto.UserDto;
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
	public ResponseEntity<Object> createUser(CreateUserDto dto) {
		// check username exist and email
		Optional<User> optUsername = repository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());

		if (optUsername.isPresent()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "Username or email is exist",
					false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}

		// Find Role
		Optional<Role> optRole = roleRepository.findByName(dto.getRoleName());

		if (optRole.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "Role is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}

		Set<Role> roles = new HashSet<>();

		roles.add(optRole.get());

		User newUser = new User();
		newUser.setUsername(dto.getUsername());
		newUser.setEmail(dto.getEmail());
		newUser.setPassword(encoder.encode(dto.getPassword()));
		newUser.setStatus(UserStatus.ACTIVE);
		newUser.setCreateAt(DateUtils.getLocalDateNow());
		newUser.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		newUser.setRoles(roles);

		repository.save(newUser);
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User create is success", true);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.ok().body(repository.findBy(UserDto.class));
	}

		@Override
	public ResponseEntity<Object> deleteUser(long id) {
		Optional<User> opUser = repository.findById(id);
		if (!opUser.isPresent()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		repository.delete(opUser.get());
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Delete User is success",
				opUser.get());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);

	}

	@Override
	public ResponseEntity<Object> findUserById(long id) {
		Optional<UserDto> opUser = repository.findUserById(id);
		if (!opUser.isPresent()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Get User is success", opUser.get());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);

	}

	@Override
	public ResponseEntity<Object> updateUser(long id, UpdateUserDto dto) {

		Optional<User> opUser = repository.findById(id);
		if (opUser.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		Set<Role> roleList = new HashSet<>();
		if (dto.getRoleIds() != null && dto.getRoleIds().length >= 1) {
			for (Long idRole : dto.getRoleIds()) {
				Optional<Role> opRole = roleRepository.findById(idRole);
				if (!opRole.isPresent()) {
					Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "Role not exist", false);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
				} else {
					roleList.add(opRole.get());
				}
			}
		}
		opUser.get().setName(dto.getName());
		opUser.get().setEmail(dto.getEmail());
		opUser.get().setPassword(dto.getPassword());
		opUser.get().setVersion(opUser.get().getVersion() + 1);
		opUser.get().setUpdatedAt(DateUtils.getLocalDateNow());
		opUser.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		opUser.get().setRoles(roleList);
		User userSave = repository.save(opUser.get());
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "User update success", userSave);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);

	}

	@Override
	public ResponseEntity<Object> deactiveUser(long id) {
		Optional<User> opUser = repository.findById(id);
		if (opUser.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		if (opUser.get().getStatus() == UserStatus.INACTIVE) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User status is INACTIVE", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		opUser.get().setStatus(UserStatus.INACTIVE);
		repository.save(opUser.get());
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "User deactive is success", true);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> activeUser(long id) {
		Optional<User> opUser = repository.findById(id);
		if (opUser.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		if (opUser.get().getStatus() == UserStatus.ACTIVE) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "User status is ACTIVE", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		opUser.get().setStatus(UserStatus.ACTIVE);
		repository.save(opUser.get());
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "User active is success", true);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

}
