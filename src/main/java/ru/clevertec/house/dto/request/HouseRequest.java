package ru.clevertec.house.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseRequest {

    Double area;
    String country;
    String city;
    String street;
    Integer numberHouse;
}
