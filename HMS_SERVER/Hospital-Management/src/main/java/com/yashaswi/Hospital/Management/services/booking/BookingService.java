package com.yashaswi.Hospital.Management.services.booking;

import com.yashaswi.Hospital.Management.models.entities.Booking;
import com.yashaswi.Hospital.Management.models.responses.*;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface BookingService {
    AllBookingResponse getAllBookingByUserId(Long userId);
    NewBookingDetailResponse createNewBooking(NewBookingRequest newBookingRequest,Long userId);
     List<Booking> getAllActiveBookingsOfUser(Long userId);

    Optional<Booking> checkIfBookingExistsAlready(String name, String doctorName, Timestamp bookingDate, boolean activeBooking, Long userId);

    NewBookingDetailResponse getBookingDetailsByBookingId(Long bookingId) ;

    Page<Booking> getAllBookingByDoctorId(Long doctorId);

    UserDetailsResponse getUserDetailsById(Long userId);
}
