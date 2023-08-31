package com.yashaswi.Hospital.Management.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yashaswi.Hospital.Management.models.enums.DoctorSpecialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;
    private String  doctorName;
    @Enumerated(EnumType.STRING)
    private DoctorSpecialization specialization;

    @OneToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
