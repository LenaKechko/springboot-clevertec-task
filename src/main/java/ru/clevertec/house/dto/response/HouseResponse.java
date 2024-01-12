package ru.clevertec.house.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponse {

    UUID uuid;
    Double area;
    String country;
    String city;
    String street;
    Integer numberHouse;

    //        @JsonFormat(pattern = )
    LocalDateTime createDate;

}
