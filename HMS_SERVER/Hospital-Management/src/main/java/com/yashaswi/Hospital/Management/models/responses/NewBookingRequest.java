package com.yashaswi.Hospital.Management.models.responses;

import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import com.yashaswi.Hospital.Management.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBookingRequest {
    String patientName;
    int patientAge;
    Gender gender;
    Long bookingTime;
    Long doctorId;

}
