package com.yashaswi.Hospital.Management;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



@SpringBootApplication
@EnableAspectJAutoProxy
public class HospitalManagementApplication {


    public static void main(String[] args) {

        String dummy = System.getenv("mynewvar");
        System.out.println(dummy);

        SpringApplication.run(HospitalManagementApplication.class, args);

    }


}
