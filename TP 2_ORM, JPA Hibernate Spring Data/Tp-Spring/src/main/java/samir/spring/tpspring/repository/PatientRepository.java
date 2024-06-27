package samir.spring.tpspring.repository;

import samir.spring.tpspring.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository <Patient, Long> {
     Patient findByNomContaining(String kw);
     Patient findByNom(String nom);
     List<Patient> findAll();
     List<Patient> findPatientById(Long id);
     @Query("select p from Patient p where p.nom like %:mc% and p.score > :s")
     List<Patient> chercher(@Param("mc") String k,@Param("s") double score);
}
