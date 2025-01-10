package com.orion.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private Long ssn;
    private String countryName;
    private Long cityId;
    private String address;
    private Long contactId;
}
