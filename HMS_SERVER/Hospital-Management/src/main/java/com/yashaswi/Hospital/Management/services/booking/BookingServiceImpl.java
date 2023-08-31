package com.yashaswi.Hospital.Management.services.booking;

import com.yashaswi.Hospital.Management.dao.BookingDao;
import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.models.entities.Booking;
import com.yashaswi.Hospital.Management.models.entities.Doctor;
import com.yashaswi.Hospital.Management.models.entities.User;
import com.yashaswi.Hospital.Management.models.enums.BookingStatus;
import com.yashaswi.Hospital.Management.models.responses.*;
import com.yashaswi.Hospital.Management.services.RuleEngine.RuleEngineService;
import com.yashaswi.Hospital.Management.services.doctor.DoctorService;
import com.yashaswi.Hospital.Management.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private  final UserService userService;
    private final BookingDao bookingDao;
    private final RuleEngineService ruleEngineService;

    private final DoctorService doctorService;


    @Autowired
    public BookingServiceImpl(UserService userService, BookingDao bookingDao, RuleEngineService ruleEngineService,DoctorService doctorService) {
        this.userService = userService;
        this.bookingDao = bookingDao;
        this.ruleEngineService = ruleEngineService;
        this.doctorService=doctorService;
    }

    int pageNumber = 0;
    int pageSize = 10;

    // Create a Pageable instance
    PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);


    // Fetch a page of bookings


    @Override
    public UserDetailsResponse getUserDetailsById(Long userId) {
        Optional<User> user = userService.getUserByUserId(userId);
        if(user.isPresent()){
            String name = user.get().getName();
            AllBookingResponse bookings = this.getAllBookingByUserId(userId);
            return UserDetailsResponse.builder()
                    .userId(userId)
                    .name(name)
                    .bookings(bookings.getBookings().stream().toList())
                    .build();
        }
        else {
            throw new UsersConflictExcept("no user found with this id");
        }

    }
    @Override
    public AllBookingResponse getAllBookingByUserId(Long userId) {
//        Optional<User> user = userService.getUserByUserId(userId);
//        if(user.isPresent()){
        Page<Booking> bookingPage = bookingDao.findAllByUserId(userId, pageRequest);
//        List<Booking> bookingList = bookingPage.stream().toList();

        if(!bookingPage.isEmpty()){
        return AllBookingResponse.builder().bookings(bookingPage).build();
        }
        else
            throw new UsersConflictExcept("can't find user or booking !");
    }
    public List<Booking> getAllActiveBookingsOfUser(Long userId){
//        return bookingDao.findAllByUser(userId);
        return null;
    }

    @Override
    public NewBookingDetailResponse createNewBooking(NewBookingRequest newBookingRequest, Long userId) {
        Optional<User> userOpt = userService.getUserByUserId(userId);
        List<Booking> bookingPage = bookingDao.findAllByUserId(userId);
        Optional<Doctor> doctorFromDao = doctorService.findDoctorById(newBookingRequest.getDoctorId());
        if(doctorFromDao.isPresent()) {
            List<Booking> doctorBookingList = bookingDao.findAllByDoctorId(doctorFromDao.get().getId());
        }
//        ruleEngineService.validateBooking(userId,bookingPage,doctorBookingList);
        Long now = (System.currentTimeMillis());

        if (userOpt.isPresent() && newBookingRequest.getBookingTime() > now ){
            Booking booking = Booking.builder()
                    .patientName(newBookingRequest.getPatientName())
                    .bookingTime(newBookingRequest.getBookingTime())
                    .user(userOpt.get())
                    .patientAge(newBookingRequest.getPatientAge())
                    .bookingStatus(BookingStatus.BOOKED)
                    .gender(newBookingRequest.getGender())
                    .doctor(doctorFromDao.get())
                    .build();

            Booking bookingObj = bookingDao.save(booking);
            return NewBookingDetailResponse.builder()
                    .bookingId(booking.getId())
                    .patientName(bookingObj.getPatientName())
                    .bookingTime(bookingObj.getBookingTime())
                    .doctorName(doctorFromDao.get().getDoctorName())
                    .doctorSpecialization(doctorFromDao.get().getSpecialization())
                    .patientAge(booking.getPatientAge())
                    .build();
        } else {
            throw new UsersConflictExcept("User not found or Booking already exists");
        }
    }

    @Override
    public Optional<Booking> checkIfBookingExistsAlready(String name, String doctorName, Timestamp bookingDate, boolean activeBooking, Long userId) {
        try {
            return bookingDao.bookingExists(name, doctorName, bookingDate, activeBooking, userId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public NewBookingDetailResponse getBookingDetailsByBookingId(Long bookingId)  {

            Optional<Booking>bookingOptional= bookingDao.findById(bookingId);

            if(bookingOptional.isPresent()) {
                Optional<Doctor> doctorOptional = doctorService.findDoctorById(bookingOptional.get().getDoctor().getId());
                    return NewBookingDetailResponse.builder()
                            .patientAge(bookingOptional.get().getPatientAge())
                            .bookingId(bookingOptional.get().getId())
                            .doctorSpecialization(doctorOptional.get().getSpecialization())
                            .doctorName(doctorOptional.get().getDoctorName())
                            .bookingTime(bookingOptional.get().getBookingTime())
                            .patientName(bookingOptional.get().getPatientName())
                            .build();
            }
            else
                throw new UsersConflictExcept("Booking not found for booking id");
    }

    @Override
    public Page<Booking> getAllBookingByDoctorId(Long doctorId) {
        Page<Booking> bookingList = bookingDao.findAllByDoctorId(doctorId,pageRequest);
        if(bookingList.isEmpty()){
            throw new UsersConflictExcept("no bookings found");
        }
        else
            return bookingList;
    }



}
