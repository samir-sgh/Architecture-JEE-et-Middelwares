package net.samir.tp3_hospital.security.repo;

import net.samir.tp3_hospital.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
