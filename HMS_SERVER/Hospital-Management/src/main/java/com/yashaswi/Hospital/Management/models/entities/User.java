package com.yashaswi.Hospital.Management.models.entities;

import com.yashaswi.Hospital.Management.models.enums.AccessorType;
import com.yashaswi.Hospital.Management.models.enums.Gender;
import com.yashaswi.Hospital.Management.models.enums.UserAccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @Enumerated (EnumType.STRING)
    private Gender gender;
    private List<String> attr;
    private String mailId;

    @Enumerated (EnumType.STRING)
    private AccessorType type;

    @Enumerated (EnumType.STRING)
    private UserAccountStatus userAccountStatus;
    private Timestamp createdAt;
    private String password;
    private String userName;

//    @OneToMany(
//            mappedBy = "user",
//            orphanRemoval = true,
//            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}
//    )
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Booking> bookings = new ArrayList<>();


//    public void addBooking(Booking booking){
//        if(!this.bookings.contains(booking)){
//            this.bookings.add(booking);
//            booking.setUser(this);
//        }
//    }
//    public void removeBooking(Booking booking){
//        if(this.bookings.contains(booking)){
//            this.bookings.remove(booking);
//            booking.setUser(null);
//        }
//    }

    public void setPassword(String password) {
        this.password = "hash+" + password;
    }

    // Getters and setters for other fields...

    public boolean isPasswordValid(String password) {
        return String.valueOf("hash+" + password).equals(this.password);

    }

//    public static MyBuilder myBuilder() {
//        return new MyBuilder();
//    }
//
//     public static class MyBuilder{
//
//        User user;
//
//        MyBuilder(){
//            this.user = new User();
//        }
//
//        public MyBuilder userId(Long id){
//            this.user.userId = id;
//            return this;
//        }
//
//        public MyBuilder name(String name){
//            this.user.name = name;
//            return this;
//        }
//
//        public User build(){
//            return this.user;
//        }
//
//        public String compact(){
//            return "name:"+this.user.name+",id:"+this.user.userId;
//        }
//    }
}
