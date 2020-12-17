package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private String cityName;
    private long area;
    private long population;
    private long gdp;
    private String description;
    @ManyToOne
    private Country country;

    public City() {
    }

    public City(Long cityId, String cityName, long area, long population, long gdp, String description, Country country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.country = country;
    }
}
