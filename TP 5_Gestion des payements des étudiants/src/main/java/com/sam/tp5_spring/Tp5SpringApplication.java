package com.sam.tp5_spring;

import com.sam.tp5_spring.entities.Payment;
import com.sam.tp5_spring.entities.PaymentStatus;
import com.sam.tp5_spring.entities.PaymentType;
import com.sam.tp5_spring.entities.Student;
import com.sam.tp5_spring.repository.PaymentRepository;
import com.sam.tp5_spring.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class Tp5SpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(Tp5SpringApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository
            , PaymentRepository paymentRepository){
        return args->{
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Said").code("8957").lastName("khoyi")
                    .programId("Reseau")
                    .build());
            //2eme
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Btisam").code("6425").lastName("Namra")
                    .programId("II-BDCC")
                    .build());
            //3eme
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Idriss").code("3285").lastName("Chdid")
                    .programId("Reseau")
                    .build());
            //4eme
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Souad").code("4698").lastName("Lgima")
                    .programId("II-BDCC")
                    .build());
            PaymentType[] paymentTypes=PaymentType.values();
            Random random=new Random();
            studentRepository.findAll().forEach(st->{
                for(int i=0;i<10;i++){
                    int index =random.nextInt(paymentTypes.length);
                    Payment payment=Payment.builder()
                            .amount(2000+(int)(Math.random()*30000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });
        };

    }

}