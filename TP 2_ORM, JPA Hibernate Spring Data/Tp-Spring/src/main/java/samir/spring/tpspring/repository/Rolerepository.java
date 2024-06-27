package samir.spring.tpspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samir.spring.tpspring.entities.Role;
@Repository
public interface Rolerepository extends JpaRepository<Role,  Long> {
    Role findByRoleName(String roleName);
}
