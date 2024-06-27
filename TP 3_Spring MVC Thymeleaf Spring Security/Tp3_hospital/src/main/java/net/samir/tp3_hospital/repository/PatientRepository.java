package net.samir.tp3_hospital.repository;

import net.samir.tp3_hospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContainsIgnoreCase(String nom, Pageable pageable);
}
