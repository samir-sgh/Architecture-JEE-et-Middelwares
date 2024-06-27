package samir.spring.tpspring;

import org.springframework.context.annotation.Bean;
import samir.spring.tpspring.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import samir.spring.tpspring.repository.*;
import samir.spring.tpspring.service.IHospitalService;
import samir.spring.tpspring.service.UserService;


import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class TpSpringApplication{

	public static void main(String[] args) {
		SpringApplication.run(TpSpringApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		//*************Ajouter des patients********************
		//patientRepository.save(new Patient(null,"tarik", new Date(),true,15));
		//patientRepository.save(new Patient(null,"sam",new Date(),false,10));
		//patientRepository.save(new Patient(null,"fathi", new Date(),false,8));
		//*************Consulter tous les patients********************
		System.out.println("************** Consulter tous les patients ****************");
		List<Patient> ttPatient= patientRepository.findAll();
		ttPatient.forEach(t->{
			System.out.println(t);
		});

		//******************** Consulter un patient  *********************
		System.out.println("******************** Consulter un patient  *********************");
		Optional<Patient> onePatient = patientRepository.findById(1L);
		System.out.println(onePatient.get().getId());
		System.out.println(onePatient.get().getNom());
		System.out.println(onePatient.get().getMalade());
		System.out.println(onePatient.get().getDateNaissance());
		System.out.println(onePatient.get().getScore());



		//******************** Chercher des patients  *********************
		System.out.println("************* Chercher des patients *****************");
		Patient chPatients= patientRepository.findByNomContaining("f");
		System.out.println(chPatients.getId());
		System.out.println(chPatients.getNom());
		System.out.println(chPatients.getMalade());
		System.out.println(chPatients.getDateNaissance());
		System.out.println(chPatients.getScore());

		System.out.println("---------------");

		List<Patient> chPatient2=patientRepository.chercher("m",10);
		chPatient2.forEach(o->{
			System.out.println(o.getNom());
			System.out.println(o.getDateNaissance());
			System.out.println(o.getMalade());
			System.out.println(o.getScore());

		});
		//******************** Mettre à jour un patient *********************
		System.out.println("******************** Mettre à jour un patient  *********************");
		Optional<Patient>  modifierPatient = patientRepository.findById(1L);
		if (modifierPatient.isPresent()){
			Patient patient=modifierPatient.get();
			patient.setNom("hamid");
			patientRepository.save(patient);
			System.out.println(patient);
		}
		//******************** Supprimer un patient  *********************
		System.out.println("******************** Supprimer un patient  *********************");
		patientRepository.deleteById(Long.valueOf(2));

		System.out.println("******************************");
		List<Patient> checkPatient= patientRepository.findAll();
		checkPatient.forEach(t->{
			System.out.println(t);
		});
	}*/



    @Bean
	CommandLineRunner start(IHospitalService hospitalService,
							PatientRepository patientRepository,
							MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository,
							ConsultationRepository consultationRepository,
							UserService userService){
		return args -> {
			/*Stream.of("Mohammed","Samir","Saad").forEach(name -> {
				Patient patient = new Patient();
				patient.setNom(name);
				patient.setScore(10);
				patient.setMalade(false);
				patient.setDateNaissance(new Date());
				hospitalService.savePatient(patient);
			});
			Stream.of("Dr Amine","Dr Hafid","Dr Najat")
					.forEach(name -> {
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecin.setEmail(name+"@gmail.com");
						hospitalService.saveMedecin(medecin);
			});*/

			Patient patient = patientRepository.findByNom("Samir");
			Patient patient1 = patientRepository.findById(1L).orElse(null);

			Medecin medecin = medecinRepository.findById(1L).orElse(null);
			Medecin medecin1 = medecinRepository.findByNom("Dr Hafid");

			/*RendezVous rdv= new RendezVous();
			rdv.setMedecin(medecin1);
			rdv.setDateRDV(new Date());
			rdv.setStatus(StatusRDV.PENDING);
			rdv.setPatient(patient1);
			RendezVous saveRDV = hospitalService.saveRDV(rdv);
			//rendezVousRepository.save(rdv);
			System.out.println(saveRDV.getId());*/

			RendezVous rendezVous = rendezVousRepository.findById(1L).orElse(null);

			Consultation consul = new Consultation();
			consul.setDateConsultation(new Date());
			consul.setRendezVous(rendezVous);
			consul.setRapportConsultation("Rapport de la consultation");
			consultationRepository.save(consul);


			// ADD NEW ROLES
			Role admin= new Role();
			admin.setDesc("Description Admin");
			admin.setRoleName("ADMIN");

			Role userRole = new Role();
			userRole.setDesc("Description User");
			userRole.setRoleName("USER");

			Role student = new Role();
			student.setDesc("Description Student");
			student.setRoleName("STUDENT");

			// ADD NEW USERS
			User user1= new User();
			user1.setUserName("user1");
			user1.setPassword("112233");
			userService.addNewUser(user1);

			User user2= new User();
			user2.setUserName("user2");
			user2.setPassword("445566");
			userService.addNewUser(user2);

			User user3= new User();
			user3.setUserName("user3");
			user3.setPassword("778899");
			userService.addNewUser(user3);



			userService.addNewRole(admin);
			userService.addNewRole(userRole);
			userService.addNewRole(student);

			userService.addRoleToUser("user1","ADMIN");
			userService.addRoleToUser("user2","USER");
			userService.addRoleToUser("user3","STUDENT");

			try {
				User user= userService.authenticate("user2","445566");
				System.out.println(user.getUserName());
				System.out.println(user.getPassword());
				System.out.println("Roles =>");
				user.getRoles().forEach(role -> System.out.println("Roles =>"+role));
			}catch(Exception exception){
				exception.printStackTrace();
			}






		};



}
}
