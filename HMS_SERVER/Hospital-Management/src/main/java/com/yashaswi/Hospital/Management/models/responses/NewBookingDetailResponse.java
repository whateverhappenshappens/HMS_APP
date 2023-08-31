package com.yashaswi.Hospital.Management.models.responses;

import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBookingDetailResponse {
    Long bookingId;
    String patientName;
    int patientAge;
    String doctorName;
    Long bookingTime;
    DoctorSpecialization doctorSpecialization;
}
