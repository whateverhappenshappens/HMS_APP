package com.yashaswi.Hospital.Management.controllers;

import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.models.entities.Booking;
import com.yashaswi.Hospital.Management.models.responses.AllBookingResponse;
import com.yashaswi.Hospital.Management.models.responses.NewBookingDetailResponse;
import com.yashaswi.Hospital.Management.models.responses.NewBookingRequest;
import com.yashaswi.Hospital.Management.models.responses.UserDetailsResponse;
import com.yashaswi.Hospital.Management.services.authentication.Authenticated;
import com.yashaswi.Hospital.Management.services.authorization.Authorized;
import com.yashaswi.Hospital.Management.services.booking.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("hms/")
@CrossOrigin(origins = "*")
public class BookingController {
    private final BookingService bookingServiceImpl;
    @Autowired
    public BookingController(BookingService bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @Authenticated
    @Authorized(allowedRoles = {"USER","ADMIN"})
    @GetMapping("user/{userId}/user-details")
    ResponseEntity<UserDetailsResponse> getUserDetailsById(@PathVariable("userId")Long userId){
        try {
            return new ResponseEntity<>(bookingServiceImpl.getUserDetailsById(userId), HttpStatus.ACCEPTED);
        }
        catch (UsersConflictExcept e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    joint cut
@CrossOrigin(origins = "*")
    @Authenticated
    @Authorized(allowedRoles = {"USER","ADMIN"})
    @GetMapping("user/{userId}/get-all-bookings")
    public ResponseEntity<AllBookingResponse> getAllBookingByUserId(@PathVariable("userId")Long userId){
        try{

            return new ResponseEntity<>( bookingServiceImpl.getAllBookingByUserId(userId),HttpStatus.OK);
        }
        catch (UsersConflictExcept usersConflictExcept){
            log.error(usersConflictExcept.getMessage(),usersConflictExcept);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin(origins = "*")
    @Authenticated
    @Authorized(allowedRoles = {"USER","ADMIN"})
    @PostMapping("user/{userId}/create-new-booking")
    public ResponseEntity<NewBookingDetailResponse> createNewBookingUsingUserId(@RequestBody NewBookingRequest newBookingRequest,
                                                                               @PathVariable("userId")Long userId){
        try{
            return new ResponseEntity<>(bookingServiceImpl.createNewBooking(newBookingRequest,userId),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Error", e);
            return new ResponseEntity<>(HttpStatus.valueOf(409));
        }

    }
    @Authenticated
    @Authorized(allowedRoles = {"ADMIN","USER","DOCTOR"})
    @GetMapping("booking/{bookingId}/get-booking-detail")
    public ResponseEntity<NewBookingDetailResponse> getBookingByBookingId(@PathVariable("bookingId")Long bookingId){
        try{
           return new ResponseEntity<>(bookingServiceImpl.getBookingDetailsByBookingId(bookingId),HttpStatus.OK);
        }
        catch (UsersConflictExcept usersConflictExcept){
           log.error(usersConflictExcept.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @Authenticated
    @Authorized(allowedRoles = {"ADMIN","DOCTOR"})
    @GetMapping("booking/{doctorId}/get-all-bookings")
    public ResponseEntity<Page<Booking>> getAllBookingsByDoctorId(@PathVariable("doctorId")Long doctorId){
        try{
            return new ResponseEntity<>(bookingServiceImpl.getAllBookingByDoctorId(doctorId),HttpStatus.OK);
        }
        catch (UsersConflictExcept usersConflictExcept){
            log.error(usersConflictExcept.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
