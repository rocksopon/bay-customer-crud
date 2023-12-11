package com.bay.demo.repository;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cif;

    private String titleEn;

    private String firstNameEn;

    private String lastNameEn;

    private String middleNameEn;

    @Past(message = "The date of birth must be past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String nationality;

    @Email(message = "The email is not a valid email.")
    private String email;
}
