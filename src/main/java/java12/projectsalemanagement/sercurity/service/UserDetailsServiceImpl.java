package java12.projectsalemanagement.sercurity.service;

import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.sercurity.dto.UserDetailsDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    private UserRepository repository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsernameWithRoles(username);

        if(!user.isPresent())
            throw new UsernameNotFoundException("Username is not existed.");

        Set<GrantedAuthority> authorities = getAuthorities(user.get().getRoles());

        return new UserDetailsDto(username, user.get().getPassword(), authorities);
    }
//private
public Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }


}
