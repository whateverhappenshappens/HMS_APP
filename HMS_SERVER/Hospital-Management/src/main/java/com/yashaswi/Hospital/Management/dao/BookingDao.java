package com.yashaswi.Hospital.Management.dao;

import com.yashaswi.Hospital.Management.models.entities.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {

//    @Query(value = "select * from booking where user_id = ?1", nativeQuery = true)
//    List<Booking> findByUser(User user);

    Optional<Booking>findById(Long id);
    Page<Booking> findAllByUser(Long userId, Pageable pageable);
    Page<Booking> findAllByUserId(Long userId, Pageable pageable);
    List<Booking> findAllByUserId(Long userId);

    @Query(value = "SELECT * FROM booking b " + "WHERE b.name = :name " + "AND b.doctor_name = :doctorName " + "AND DATE(b.booking_time) = :bookingDate " + "AND b.active_booking = :activeBooking " + "AND b.user_id = :userId", nativeQuery = true)
    Optional<Booking> bookingExists(@Param("name") String name, @Param("doctorName") String doctorName,
                                @Param("bookingDate") Timestamp bookingDate, @Param("activeBooking") boolean activeBooking,
                                @Param("userId") Long userId);

    Page<Booking> findAllByDoctorId(Long doctorId,Pageable pageable);
    List<Booking> findAllByDoctorId(Long doctorId);

//    List<Booking> findAllByUser(Long userId);


//    "SELECT b FROM Booking b "+
//            "WHERE b.name = :name "+
//            "AND b.doctorName = :doctorName "+
//            "AND FUNCTION('DATE', b.bookingTime) = :bookingDate "+
//            "AND b.activeBooking = :activeBooking"+
//            "AND b.user_id = :userId"
//    Optional<Booking> findByNameAndDoctorNameAndBookingTimeAndActiveBookingAndUserId(String name,
//                                                                                     String doctorName,
//
//                                                                                     );

}
