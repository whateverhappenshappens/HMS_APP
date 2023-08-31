package com.yashaswi.Hospital.Management.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yashaswi.Hospital.Management.models.enums.BookingStatus;
import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import com.yashaswi.Hospital.Management.models.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.util.UUID;

import static jakarta.persistence.ConstraintMode.CONSTRAINT;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Booking {
    @Id
    @GeneratedValue
    private Long Id;
    private String patientName;
    private int patientAge;
    @Enumerated (EnumType.STRING)
    private Gender gender;
    private Long bookingTime;
    @Enumerated (EnumType.STRING)
    private BookingStatus bookingStatus;

//    only a  is a unidirectional mapping is required between Users and Booking i.e.
//    Many booking can have same user
//    @jsonIgnore ignores any Type of data that can gives error or is unable to be passed because of any reason
//    OnDelete says that when user is deleted then all bookings associated with that userid is also deleted
    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;

    public Boolean isValid(){
        Long now =  System.currentTimeMillis();
        return bookingTime > now;
    }

//    @Override
//    public String toString() {
//        return "Booking{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", doctorName='" + doctorName + '\'' +
//                ", bookingTime='" + bookingTime + '\'' +
//                ", activeBookings='" + activeBookings + '\'' +
//                ", user='" + user+ '\'' +
//                '}';
//    }
}

