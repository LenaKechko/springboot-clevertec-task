package ru.clevertec.house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.house.entity.model.Sex;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    UUID uuid;
    String name;
    String surname;
    Sex sex;
    String passportSeries;
    String passportNumber;
    ZonedDateTime createDate;
    ZonedDateTime updateDate;
    List<HouseDto> ownHouses;
    HouseDto liveHouse;
}
