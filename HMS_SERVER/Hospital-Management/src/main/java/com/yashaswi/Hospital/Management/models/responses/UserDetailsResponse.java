package com.yashaswi.Hospital.Management.models.responses;

import com.yashaswi.Hospital.Management.models.entities.Booking;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    Long userId;
    String name;
    List<Booking> bookings;
}
