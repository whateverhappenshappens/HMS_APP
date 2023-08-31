package com.yashaswi.Hospital.Management.models.responses;

import com.yashaswi.Hospital.Management.models.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllDoctorResponse {
    Page<Doctor> doctorList;
}
