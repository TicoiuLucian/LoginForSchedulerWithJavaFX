package ro.itschool.scheduler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.itschool.scheduler.entity.MyUser;
import ro.itschool.scheduler.repository.RoleRepository;
import ro.itschool.scheduler.repository.UserRepository;
import ro.itschool.scheduler.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @RequestMapping("/all")
    public List<MyUser> getAllUsers() {
        return userService.findAll();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public MyUser saveUser(@RequestBody MyUser u) {
        return userService.saveUser(u);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/update-password")
    public MyUser updateUser(@RequestBody MyUser u) {
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        return userRepository.save(u);
    }
}
