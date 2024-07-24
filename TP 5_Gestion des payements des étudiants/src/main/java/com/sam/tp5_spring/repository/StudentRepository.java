package com.sam.tp5_spring.repository;

import com.sam.tp5_spring.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student,String>{
    Student findByCode(String code);
    //List<Student> findByProgramId(String programId);


}
