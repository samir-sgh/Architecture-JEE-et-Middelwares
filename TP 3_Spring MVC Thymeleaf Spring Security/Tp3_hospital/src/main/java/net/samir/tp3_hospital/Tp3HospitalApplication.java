package net.samir.tp3_hospital;

import net.samir.tp3_hospital.entities.Patient;
import net.samir.tp3_hospital.repository.PatientRepository;
import net.samir.tp3_hospital.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp3HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp3HospitalApplication.class, args);
    }



    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {


            Patient p1 = Patient.builder()
                    .id(null)
                    .nom("samir")
                    .prenom("sghir")
                    .dateNaissance(new Date()).score(190)
                    .build();

            Patient p2 = new Patient();
            p2.setId(null);
            p2.setNom("anas");
            p2.setPrenom("hajri");
            p2.setDateNaissance(new Date());
            p2.setMalade(true);
            p2.setScore(150);

            Patient p3 = new Patient(null,"Mohammed","kada",new Date(),true,200);

            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);

            List<Patient> patients = patientRepository.findAll();
            patients.forEach(patient -> System.out.println(patient.toString()));
        };
    };
    //@Bean
    CommandLineRunner start(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {

            jdbcUserDetailsManager.createUser(
                    User.withUsername("user11").password(passwordEncoder().encode("12345")).roles("USER").build());
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user22").password(passwordEncoder().encode("12345")).roles("USER").build());
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin2").password(passwordEncoder().encode("12345")).roles("USER","ADMIN").build());
        };
    }

    //@Bean
    CommandLineRunner CommandeLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin","1234","admin@gmail.com","1234");

            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","ADMIN");
        };

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

}
