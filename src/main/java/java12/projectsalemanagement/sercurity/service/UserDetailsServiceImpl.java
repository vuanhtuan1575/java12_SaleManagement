package java12.projectsalemanagement.sercurity.service;

import java12.projectsalemanagement.sercurity.dto.UserDetailsDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Service
public class UserDetailsServiceImpl  {
//    private UserRepository repository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        repository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = repository.findByUsernameWithGroups(username);
//
//        if(!user.isPresent())
//            throw new UsernameNotFoundException("Username is not existed.");
//
//        Set<GrantedAuthority> authorities = getAuthorities(user.get().getGroups());
//
//        return new UserDetailsDto(username, user.get().getPassword(), authorities);
//    }
//
//    private Set<GrantedAuthority> getAuthorities(Set<Group> groups) {
//        Set<GrantedAuthority> authorities = new HashSet<>();
//
//        for(Group group : groups) {
//            authorities.add(new SimpleGrantedAuthority(group.getName()));
//        }
//
//        return authorities;
//    }
}
