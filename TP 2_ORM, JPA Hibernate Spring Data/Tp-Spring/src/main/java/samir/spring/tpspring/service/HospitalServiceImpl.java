package samir.spring.tpspring.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import samir.spring.tpspring.entities.Consultation;
import samir.spring.tpspring.entities.Medecin;
import samir.spring.tpspring.entities.Patient;
import samir.spring.tpspring.entities.RendezVous;
import samir.spring.tpspring.repository.ConsultationRepository;
import samir.spring.tpspring.repository.MedecinRepository;
import samir.spring.tpspring.repository.PatientRepository;
import samir.spring.tpspring.repository.RendezVousRepository;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, ConsultationRepository consultationRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsul(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
