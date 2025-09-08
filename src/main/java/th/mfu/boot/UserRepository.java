package th.mfu.boot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();                     // to return all users
    User findByUsername(String username); 
}
