package com.example.Crud.operation.and.validation.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank(message = "Username can not be blank")
    private String userName;

    @NotBlank(message ="date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfBirth;
    private String email;

    @NotBlank(message = "phone number  required")
    @Size(min=12,max = 12,message= "size must be 10")
    private String phoneNumber;


    private String date;
    private String time;
}

