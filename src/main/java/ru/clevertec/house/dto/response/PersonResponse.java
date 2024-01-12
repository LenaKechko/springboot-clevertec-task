package ru.clevertec.house.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.house.entity.model.Passport;
import ru.clevertec.house.entity.model.Sex;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    UUID uuid;
    String name;
    String surname;
    Sex sex;
    Passport passport;
    LocalDateTime createDate;
    LocalDateTime updateDate;
    List<HouseResponse> ownHouses;
    HouseResponse liveHouse;
}
