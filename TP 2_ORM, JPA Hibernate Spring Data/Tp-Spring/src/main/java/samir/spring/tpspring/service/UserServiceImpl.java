package samir.spring.tpspring.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samir.spring.tpspring.entities.Role;
import samir.spring.tpspring.entities.User;
import samir.spring.tpspring.repository.Rolerepository;
import samir.spring.tpspring.repository.UserRepository;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Rolerepository rolerepository;


    @Override
    public User addNewUser(User user) {
        user.setPassword(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return rolerepository.save(role);
    }

    @Override
    public User findUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return rolerepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = findUserByUsername(userName);
        Role role = findRoleByRoleName(roleName);
        if (user.getRoles()!=null) {
        user.getRoles().add(role);
        role.getUsers().add(user);
        }
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUserName(username);
        if (user != null) throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)) {
            throw new RuntimeException("Bad credentials");
        }
        return user;
    }
}
