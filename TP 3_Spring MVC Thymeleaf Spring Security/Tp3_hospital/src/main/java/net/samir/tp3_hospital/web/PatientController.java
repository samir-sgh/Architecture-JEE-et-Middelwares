package net.samir.tp3_hospital.web;


import jakarta.validation.Valid;
import net.samir.tp3_hospital.entities.Patient;
import net.samir.tp3_hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size",defaultValue = "5") int size,
                        @RequestParam(name="keyword", defaultValue = "") String keyword){
        Page<Patient> pagePatient = patientRepository.findByNomContainsIgnoreCase(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatient", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping("/admin/deletePatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePatient ( Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home (){
        return "redirect:/user/index";
    }
    @GetMapping("/admin/formPatients")
    public String formPatient (Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors())return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page", page);
        return "editPatient";
    }


}
