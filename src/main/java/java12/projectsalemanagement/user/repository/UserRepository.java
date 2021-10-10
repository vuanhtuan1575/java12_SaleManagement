package java12.projectsalemanagement.user.repository;

import java12.projectsalemanagement.user.enitty.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.groups WHERE u.username = ?1")
//    Optional<User> findByUsernameWithGroups(String username);

}
