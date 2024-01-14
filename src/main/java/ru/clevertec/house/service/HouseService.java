package ru.clevertec.house.service;

import ru.clevertec.house.dto.response.PersonResponse;

import java.util.List;
import java.util.UUID;

public abstract class HouseService<HouseResponse, HouseRequest>
        implements IService<HouseResponse, HouseRequest> {

    public abstract List<PersonResponse> getPersonsLivingInHouse(UUID uuid);
}
