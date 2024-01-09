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
import ru.clevertec.house.entity.model.Sex;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    UUID uuid;
    @Column
    String name;
    @Column
    String surname;
    @Column
    Sex sex;
    @Column(name = "passport_series")
    String passportSeries;
    @Column(name = "passport_number")
    String passportNumber;
    @Column(name = "create_date")
    ZonedDateTime createDate;
    @Column(name = "update_date")
    ZonedDateTime updateDate;

    @ManyToMany
    @JoinTable(name = "owners",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_house"))
    List<House> ownHouses;
    @ManyToMany
    @JoinTable(name = "residents",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_house"))
    House liveHouse;

    public void addHouseToOwn(House house) {
        if (ownHouses == null) {
            ownHouses = new ArrayList<>();
        }
        ownHouses.add(house);
    }

}
