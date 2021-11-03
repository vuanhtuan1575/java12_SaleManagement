package java12.projectsalemanagement.user.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java12.projectsalemanagement.user.dto.UserDto;
import java12.projectsalemanagement.user.enitty.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.groups WHERE u.username = ?1")
//    Optional<User> findByUsernameWithGroups(String username);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = ?1")
    Optional<User> findByUsernameWithRoles(String username);
    
    //Projection dynamic
    <T> List<T> findBy(Class<T> projection);
    
    @Query("SELECT u FROM User u WHERE id=?1")
    Optional<UserDto> findUserById(long id);
    
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    Optional<User> findByUsername(String username);

}
