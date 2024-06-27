package samir.spring.tpspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samir.spring.tpspring.entities.Consultation;
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
