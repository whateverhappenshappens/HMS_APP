package com.yashaswi.Hospital.Management.models.responses;

import com.yashaswi.Hospital.Management.models.entities.Booking;
import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllBookingResponse {
    Page<Booking> bookings;
}
