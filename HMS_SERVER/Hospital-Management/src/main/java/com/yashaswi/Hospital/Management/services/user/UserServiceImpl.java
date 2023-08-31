package com.yashaswi.Hospital.Management.services.user;

import com.yashaswi.Hospital.Management.dao.DoctorDao;
import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.dao.UserDao;

import com.yashaswi.Hospital.Management.models.entities.Doctor;
import com.yashaswi.Hospital.Management.models.entities.User;
import com.yashaswi.Hospital.Management.models.enums.AccessorType;
import com.yashaswi.Hospital.Management.models.enums.UserAccountStatus;
import com.yashaswi.Hospital.Management.models.responses.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final DoctorDao doctorDao;

    @Autowired
    public UserServiceImpl (UserDao userDao, DoctorDao doctorDao){
        this.doctorDao=doctorDao;
        this.userDao=userDao;
    }

    @Override
    public UserDetailsResponse getUser(Long userId) {
        Optional<User> user = userDao.findById(userId);
        if(user.isPresent()){
            return UserDetailsResponse.builder()
                    .userId(userId)
                    .bookings(new ArrayList<>())
                    .name(user.get().getName())
                    .build();
        } else
            return UserDetailsResponse.builder().build();
    }

    @Override
    public NewUserResponse createNewUser(NewNormalUserRequest newNormalUserRequest)  {
        String emailId = newNormalUserRequest.getEmail();
        if(existsUserWithEmail(emailId)){
            throw new UsersConflictExcept("user already exists");
        }
        String username= newNormalUserRequest.getUsername();
        if(existsUserWithUsername(username)){
            throw new UsersConflictExcept("user already exists");
        }
        Timestamp now =  new Timestamp(System.currentTimeMillis());
        User user = User.builder()
                .age(newNormalUserRequest.getAge())
                .attr(newNormalUserRequest.getAttr())
                .mailId(newNormalUserRequest.getEmail())
                .name(newNormalUserRequest.getName())
                .userName(newNormalUserRequest.getUsername())
                .type(AccessorType.USER)
                .gender(newNormalUserRequest.getGender())
                .createdAt(now)
                .userAccountStatus(UserAccountStatus.ACTIVE)
                .build();
        user.setPassword(newNormalUserRequest.getPassword());
        User userResponseObj = userDao.save(user);
        return NewUserResponse.builder()
                .userId(userResponseObj.getId())
                .name(userResponseObj.getName())
                .gender(userResponseObj.getGender())
                .attr(userResponseObj.getAttr())
                .email(userResponseObj.getMailId())
                .age(userResponseObj.getAge())
                .build();
    }

    private boolean existsUserWithUsername(String username) {
        return userDao.existsUserByUserName(username);
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return userDao.existsUserByMailId(email);
    }

    @Override
    public void deleteUserById(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public User getUserByUserName(String username) {
        try {
            return userDao.findByUserName(username);
        } catch ( Exception e){
            return null;
        }
    }

    @Override
    public Optional<User> getUserByUserId(Long userId) {
        try{
            return userDao.findById(userId);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public NewUserResponse createAdmin(NewNormalUserRequest newNormalUserRequest) {
        String emailId= newNormalUserRequest.getEmail();

        if(existsUserWithEmail(emailId)){
            throw new UsersConflictExcept("user exists with this email");
        }
        if (existsUserWithUsername(newNormalUserRequest.getUsername())){
            throw new UsersConflictExcept("user exists with this username");
        }
        Timestamp now =  new Timestamp(System.currentTimeMillis());
        User user = User.builder()
                .age(newNormalUserRequest.getAge())
                .attr(newNormalUserRequest.getAttr())
                .mailId(newNormalUserRequest.getEmail())
                .name(newNormalUserRequest.getName())
                .userName(newNormalUserRequest.getUsername())
                .type(AccessorType.ADMIN)
                .gender(newNormalUserRequest.getGender())
                .createdAt(now)
                .userAccountStatus(UserAccountStatus.ACTIVE)
                .build();
        user.setPassword(newNormalUserRequest.getPassword());
        User userResponseObj = userDao.save(user);
        return NewUserResponse.builder()
                .userId(userResponseObj.getId())
                .name(userResponseObj.getName())
                .gender(userResponseObj.getGender())
                .attr(userResponseObj.getAttr())
                .email(userResponseObj.getMailId())
                .age(userResponseObj.getAge())
                .build();

    }

    @Override
    @Transactional
    public boolean blockUserAccount(Long userId) {
        Optional<User> user = userDao.findById(userId);
        if(user.isPresent()){
            user.get().setUserAccountStatus(UserAccountStatus.BLOCKED);
            return true;
        }
        else {
            throw new UsersConflictExcept("user not found for this id");
        }
    }

    @Override
    @Transactional
    public boolean deactivateUserAccount(Long userId) {
        Optional<User> user = userDao.findById(userId);
        if(user.isPresent()){
            user.get().setUserAccountStatus(UserAccountStatus.DEACTIVATED);
            return true;
        }
        else {
            throw new UsersConflictExcept("user not found for this id");
        }
    }

    @Override
    public NewUserResponse createDoctor(NewDoctorRequest newDoctorRequest) {
        String emailId= newDoctorRequest.getEmail();

        if(existsUserWithEmail(emailId)){
            throw new UsersConflictExcept("doctor exists with this email");
        }
        if (existsUserWithUsername(newDoctorRequest.getUsername())){
            throw new UsersConflictExcept("doctor exists with this username");
        }
        Timestamp now =  new Timestamp(System.currentTimeMillis());
        User user = User.builder()
                .age(newDoctorRequest.getAge())
                .attr(newDoctorRequest.getAttr())
                .mailId(newDoctorRequest.getEmail())
                .name(newDoctorRequest.getName())
                .userName(newDoctorRequest.getUsername())
                .type(AccessorType.DOCTOR)
                .gender(newDoctorRequest.getGender())
                .createdAt(now)
                .userAccountStatus(UserAccountStatus.ACTIVE)
                .build();
        user.setPassword(newDoctorRequest.getPassword());
        User userResponseObj = userDao.save(user);
        Doctor doctor= Doctor.builder()
                .user(userResponseObj)
                .doctorName(newDoctorRequest.getName())
                .specialization(newDoctorRequest.getSpecialization())
                .build();
        doctorDao.save(doctor);
        return NewUserResponse.builder()
                .userId(userResponseObj.getId())
                .name(userResponseObj.getName())
                .gender(userResponseObj.getGender())
                .attr(userResponseObj.getAttr())
                .email(userResponseObj.getMailId())
                .age(userResponseObj.getAge())
                .build();
    }

    @Override
    @Transactional
    public boolean reactivateUserAccount(Long userId) {
        Optional<User> user = userDao.findById(userId);
        if(user.isPresent() && user.get().getUserAccountStatus() == UserAccountStatus.DEACTIVATED || user.get().getUserAccountStatus() == UserAccountStatus.BLOCKED){
            user.get().setUserAccountStatus(UserAccountStatus.ACTIVE);
            return true;
        }
        else {
            throw new UsersConflictExcept("user not found for this id");
        }
    }



//    public static void main(String[] args) {
////        UserServiceImpl userService = new UserServiceImpl();
////        userService.getUser(1L);
//        String ss = UserDetailsResponse.builder()
//                .userId(1L)
//                .bookings(new ArrayList<>())
//                .name("Zatin")
//                        .build().toString();
//
//        String testUser =  User.myBuilder().userId(1L).name("abdws").compact();
//
//        System.out.println(ss);
//    }

}
