package com.yashaswi.Hospital.Management.services.doctor;

import com.yashaswi.Hospital.Management.models.entities.Booking;
import com.yashaswi.Hospital.Management.models.entities.Doctor;
import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import com.yashaswi.Hospital.Management.models.responses.AllDoctorResponse;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    AllDoctorResponse getAllDoctors(int offset,int limit);

    List<Doctor> getAllDoctorsWithSpecialization(DoctorSpecialization doctorSpecialization);

    Doctor getDoctorByDoctorNameAndDoctorSpecialization(String doctorName,DoctorSpecialization doctorSpecialization);
    Optional<Doctor> findDoctorById(Long doctorId);
}
