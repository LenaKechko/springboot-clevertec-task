package ru.clevertec.house.service;

import ru.clevertec.house.dto.response.PersonWithoutLiveHouseResponse;

import java.util.List;
import java.util.UUID;

public interface HouseService<HouseResponse, HouseRequest>
        extends IService<HouseResponse, HouseRequest> {

    public List<PersonWithoutLiveHouseResponse> getPersonsLivingInHouse(UUID uuid);
}
