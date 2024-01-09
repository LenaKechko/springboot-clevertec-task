package ru.clevertec.house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseDto {

    UUID uuid;
    Double area;
    String country;
    String city;
    String street;
    Integer numberHouse;
    ZonedDateTime createDate;
    List<PersonDto> owners;
    List<PersonDto> residents;

}
