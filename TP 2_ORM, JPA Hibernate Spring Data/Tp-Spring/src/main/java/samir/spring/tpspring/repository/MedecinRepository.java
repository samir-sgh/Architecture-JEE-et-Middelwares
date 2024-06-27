package samir.spring.tpspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samir.spring.tpspring.entities.Medecin;
import samir.spring.tpspring.entities.Patient;
@Repository
public interface MedecinRepository extends JpaRepository <Medecin,Long> {
    Medecin findByNom(String nom);
}
