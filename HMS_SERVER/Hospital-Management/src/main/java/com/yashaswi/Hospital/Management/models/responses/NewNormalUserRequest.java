package com.yashaswi.Hospital.Management.models.responses;


import com.yashaswi.Hospital.Management.models.enums.AccessorType;
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
public class NewNormalUserRequest {
    String name;
    int age;
    List<String> attr;
    String email;
    Gender gender;
    String password;
    String username;
}
