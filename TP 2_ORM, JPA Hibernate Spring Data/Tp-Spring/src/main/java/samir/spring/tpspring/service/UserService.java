package samir.spring.tpspring.service;

import samir.spring.tpspring.entities.Role;
import samir.spring.tpspring.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName, String roleName);
    User authenticate(String username, String password);
}
