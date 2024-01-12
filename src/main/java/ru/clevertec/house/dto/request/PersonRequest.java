package ru.clevertec.house.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.house.entity.model.Passport;
import ru.clevertec.house.entity.model.Sex;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    String name;
    String surname;
    Sex sex;
    Passport passport;
//    HouseRequest house;
}
