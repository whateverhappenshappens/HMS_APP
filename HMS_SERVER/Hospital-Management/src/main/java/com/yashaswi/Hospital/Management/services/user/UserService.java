package com.yashaswi.Hospital.Management.services.user;

import com.yashaswi.Hospital.Management.models.entities.User;
import com.yashaswi.Hospital.Management.models.responses.NewDoctorRequest;
import com.yashaswi.Hospital.Management.models.responses.NewNormalUserRequest;
import com.yashaswi.Hospital.Management.models.responses.NewUserResponse;
import com.yashaswi.Hospital.Management.models.responses.UserDetailsResponse;
import jakarta.transaction.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

public interface UserService {

    UserDetailsResponse getUser(Long userId);

    NewUserResponse createNewUser(NewNormalUserRequest newUserResponse) throws AccountNotFoundException;
    boolean existsUserWithEmail(String email);

    @Transactional
    void deleteUserById(Long userId);

    User getUserByUserName(String username);
    Optional<User> getUserByUserId(Long userId);

    NewUserResponse createAdmin(NewNormalUserRequest newNormalUserRequest);

    boolean blockUserAccount(Long userId);

    boolean deactivateUserAccount(Long userId);

    NewUserResponse createDoctor(NewDoctorRequest newDoctorRequest);

    boolean reactivateUserAccount(Long userId);


}
