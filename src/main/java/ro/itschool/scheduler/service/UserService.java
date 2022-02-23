package ro.itschool.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.scheduler.entity.MyUser;
import ro.itschool.scheduler.entity.Role;
import ro.itschool.scheduler.repository.RoleRepository;
import ro.itschool.scheduler.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public MyUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public MyUser findUserByUserName(String userName) {
        return userRepository.findByUserNameIgnoreCase(userName);
    }

    public boolean findUserByUserNameAndPassword(String userName, String password) {
        final Optional<MyUser> myUser = Optional.ofNullable(userRepository.findByUserNameIgnoreCase(userName));
        return myUser.filter(user -> BCrypt.checkpw(password, user.getPassword())).isPresent();
    }

    public MyUser saveUser(MyUser u) {
        MyUser user = new MyUser(u);
        user.setEmail(u.getEmail());
        user.setActive(true);
        user.setUserName(u.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        user.setFullName(u.getFullName());
        u.getRoles().forEach(role -> {
            final Role roleByName = roleRepository.findByName(role.getName());
            if (roleByName == null)
                user.getRoles().add(roleRepository.save(role));
            else {
                role.setId(roleByName.getId());
            }
        });
        return userRepository.save(user);
    }

    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

}
