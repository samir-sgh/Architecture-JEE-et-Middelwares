package samir.spring.tpspring.service;

import samir.spring.tpspring.entities.Consultation;
import samir.spring.tpspring.entities.Medecin;
import samir.spring.tpspring.entities.Patient;
import samir.spring.tpspring.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsul(Consultation consultation);
}
