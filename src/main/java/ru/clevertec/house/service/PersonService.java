package ru.clevertec.house.service;

import ru.clevertec.house.dto.response.HouseResponse;

import java.util.List;
import java.util.UUID;

public abstract class PersonService<PersonResponse, PersonRequest> implements IService<PersonResponse, PersonRequest> {

    public abstract List<HouseResponse> getOwningHouses(UUID uuid);
}
