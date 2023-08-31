package com.yashaswi.Hospital.Management.controllers;

import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.models.entities.Doctor;
import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import com.yashaswi.Hospital.Management.models.responses.AllDoctorResponse;
import com.yashaswi.Hospital.Management.services.doctor.DoctorService;
import com.yashaswi.Hospital.Management.services.doctor.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hms/")
@CrossOrigin
public class DoctorController {
    private final DoctorService doctorServiceImpl;

    @Autowired
    public DoctorController (DoctorService doctorService){
        this.doctorServiceImpl=doctorService;
    }

    @GetMapping("all-doctors")
    public ResponseEntity<AllDoctorResponse> allDoctorsList(@RequestParam(defaultValue = "0") int offset,
                                                            @RequestParam(defaultValue = "10") int limit){
        try {

            return new ResponseEntity<>(doctorServiceImpl.getAllDoctors(offset,limit), HttpStatus.OK);
        }
        catch (UsersConflictExcept e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("all-doctors/{specialization}")
    public ResponseEntity<List<Doctor>> allDoctorsList(@PathVariable("specialization") DoctorSpecialization doctorSpecialization){
        try {

            return new ResponseEntity<>(doctorServiceImpl.getAllDoctorsWithSpecialization(doctorSpecialization), HttpStatus.OK);
        }
        catch (UsersConflictExcept e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
