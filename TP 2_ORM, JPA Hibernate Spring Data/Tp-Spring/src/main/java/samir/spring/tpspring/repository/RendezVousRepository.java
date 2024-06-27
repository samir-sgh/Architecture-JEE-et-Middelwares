package samir.spring.tpspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samir.spring.tpspring.entities.RendezVous;
@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

}
