package com.yashaswi.Hospital.Management.models.responses;

import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import com.yashaswi.Hospital.Management.models.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDoctorRequest {
    String name;
    int age;
    List<String> attr;
    String email;
    Gender gender;
    String password;
    String username;
    DoctorSpecialization specialization;
}
