package ru.clevertec.house.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "houses")
@Data
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    UUID uuid;
    @Column
    Double area;
    @Column
    String country;
    @Column
    String city;
    @Column
    String street;
    @Column(name = "number")
    Integer numberHouse;
    @Column(name = "create_date")
    ZonedDateTime createDate;
    @ManyToMany
    @JoinTable(name = "owners",
            joinColumns = @JoinColumn(name = "id_house"),
            inverseJoinColumns = @JoinColumn(name = "id_person"))
    List<Person> owners;
    @ManyToMany
    @JoinTable(name = "residents",
            joinColumns = @JoinColumn(name = "id_house"),
            inverseJoinColumns = @JoinColumn(name = "id_person"))
    List<Person> residents;

    public void addPersonToOwner(Person person) {
        if (owners == null) {
            owners = new ArrayList<>();
        }
        owners.add(person);
    }

    public void addPersonToResident(Person person) {
        if (residents == null) {
            residents = new ArrayList<>();
        }
        residents.add(person);
    }
}
