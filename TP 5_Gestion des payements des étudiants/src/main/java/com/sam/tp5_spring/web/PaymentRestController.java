package com.sam.tp5_spring.web;

import com.sam.tp5_spring.dtos.NewPaymentDTO;
import com.sam.tp5_spring.entities.Payment;
import com.sam.tp5_spring.entities.PaymentStatus;
import com.sam.tp5_spring.entities.PaymentType;
import com.sam.tp5_spring.entities.Student;
import com.sam.tp5_spring.repository.PaymentRepository;
import com.sam.tp5_spring.repository.StudentRepository;
import com.sam.tp5_spring.services.PaymentService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;
    public PaymentRestController(PaymentRepository paymentRepository,StudentRepository studentRepository){
        this.paymentRepository=paymentRepository;
        this.studentRepository=studentRepository;
    }
    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping("/students/{code}/payments")
    public List<Payment>paymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping("payments/byStatus")
    public List<Payment>paymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }
    @GetMapping("payments/byType")
    public List<Payment>paymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }
    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }
   // @GetMapping("/studentsByprogramId")
   //public List<Student> getStudentByProgramId(@RequestParam String programId){
     //   return studentRepository.findByProgramId(programId);
    //}
    @PutMapping("/payments/{id}")
    public Payment updaPaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        return paymentService.updaPaymentStatus(status, id);
    }
    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam("file") MultipartFile file, NewPaymentDTO newPaymentDTO)
    throws IOException {
        return this.paymentService.savePayment(file, newPaymentDTO);
    }

    @GetMapping(path = "/paymentFile/{paymentId}",produces=MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId)throws IOException{
        return paymentService.getPaymentFile(paymentId);
    }
    
}
