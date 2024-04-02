package org.example.models;

import jakarta.persistence.*;

// Сущность для hibernate
@Entity
@Table(name = "weather")
public class WeatherEntity {
    public WeatherEntity(){}

    public WeatherEntity(String city, int temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "city")
    private String city;
    @Column(name = "temperature")
    private int temperature;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
