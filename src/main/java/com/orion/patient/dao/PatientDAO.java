package com.orion.patient.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class PatientDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "ssn", nullable = false)
    private Long ssn;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contact_id", nullable = false)
    private Long contactId;
}
