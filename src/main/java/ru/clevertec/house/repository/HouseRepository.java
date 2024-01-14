package ru.clevertec.house.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.house.entity.Person;

import java.util.List;
import java.util.UUID;

@Repository
public abstract class HouseRepository<House> implements IRepository<House> {

    public abstract List<Person> findPersonsLivingInHouse(UUID uuid);
}
