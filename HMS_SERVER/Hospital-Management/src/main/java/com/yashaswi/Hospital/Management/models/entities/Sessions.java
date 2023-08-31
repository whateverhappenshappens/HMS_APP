package com.yashaswi.Hospital.Management.models.entities;

import com.yashaswi.Hospital.Management.models.enums.AccessorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sessions {
    @Id
    String token;
    Timestamp validTill;
    Long userId;

    @Enumerated(EnumType.STRING)
    AccessorType type;

    public Boolean isValid(){
        Timestamp now =  new Timestamp(System.currentTimeMillis());
        return validTill.getTime() > now.getTime();
    }
}
