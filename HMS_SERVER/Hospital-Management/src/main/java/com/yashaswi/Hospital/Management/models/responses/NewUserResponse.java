package com.yashaswi.Hospital.Management.models.responses;


import com.yashaswi.Hospital.Management.models.entities.User;
import com.yashaswi.Hospital.Management.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserResponse {
    Long userId;
    String name;
    int age;
    List<String> attr;
    String email;
    Gender gender;

    public static NewUserResponse getResponseFromUser(User user){
        return NewUserResponse.builder()
                .build();
    }

}
