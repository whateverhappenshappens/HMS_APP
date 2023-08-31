package com.yashaswi.Hospital.Management.services.doctor;

import com.yashaswi.Hospital.Management.dao.DoctorDao;
import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.models.entities.Doctor;
import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import com.yashaswi.Hospital.Management.models.responses.AllDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorServiceImpl (DoctorDao doctorDao){
        this.doctorDao=doctorDao;
    }


    int pageNumber = 0;
    int pageSize = 10;


    // Create a Pageable instance
    PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);

    @Override
    public AllDoctorResponse getAllDoctors(int offset,int limit) {
        Pageable pageable = PageRequest.of(offset,limit);
        Page<Doctor> doctors = doctorDao.findAll(pageable);
//        List<Doctor> doctorList = doctors.stream().toList();
        if(!doctors.isEmpty()){
            return AllDoctorResponse.builder().doctorList(doctors).build();
        }
        else
            throw new UsersConflictExcept("no doctors found");
    }

    @Override
    public List<Doctor> getAllDoctorsWithSpecialization(DoctorSpecialization doctorSpecialization) {
        List<Doctor> doctors = doctorDao.findAllBySpecialization(doctorSpecialization);
//        List<Doctor> doctorList = doctors.stream().toList();
        if(!doctors.isEmpty()){
            return doctors;
        }
        else
            throw new UsersConflictExcept("no doctors found for this specialization");
    }

    @Override
    public Doctor getDoctorByDoctorNameAndDoctorSpecialization(String doctorName, DoctorSpecialization doctorSpecialization) {
        return doctorDao.getDoctorByDoctorNameAndSpecialization(doctorName,doctorSpecialization);
    }

    @Override
    public Optional<Doctor> findDoctorById(Long doctorId) {
       return doctorDao.findById(doctorId);
    }


}
