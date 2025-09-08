package th.mfu.boot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // Add UserRepository with @Autowired
    @Autowired
    public UserRepository repo;

    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        // Check if user with the username exists
        if (repo.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }

        // Save the user
        repo.save(user);

        // Return proper status
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> list() {

        // Return list of users with proper status
        List<User> users = repo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        // Check if user with the id exists
        Optional<User> user = repo.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        //  Delete the user
        repo.deleteById(id);

        // Return proper status
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
