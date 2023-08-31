package com.yashaswi.Hospital.Management.dao;

import com.yashaswi.Hospital.Management.models.entities.Doctor;
import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorDao extends JpaRepository<Doctor,Long> {
    @NotNull Page<Doctor> findAll(@NotNull Pageable pageable);

    List<Doctor> findAllBySpecialization(DoctorSpecialization doctorSpecialization);
    Doctor getDoctorByDoctorNameAndSpecialization(String doctorName, DoctorSpecialization doctorSpecialization);
}
