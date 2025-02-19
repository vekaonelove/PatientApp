package com.orion.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cities")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "name", nullable = false) // Assuming country name is the PK
    private CountryEntity country;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
}
//TODO delete city, country (ony leave ids in FKs),  get this from Kar
//TODO only leave optional name for cities, transfer that to Karolina
//TODO graphQL - ?