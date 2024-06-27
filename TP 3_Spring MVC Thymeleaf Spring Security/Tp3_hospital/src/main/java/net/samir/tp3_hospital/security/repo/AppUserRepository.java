package net.samir.tp3_hospital.security.repo;

import net.samir.tp3_hospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    public AppUser findByUsername(String username);
}
