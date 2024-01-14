package ru.clevertec.house.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.EntityNotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.repository.IRepository;
import ru.clevertec.house.service.PersonService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PersonServiceImpl extends PersonService<PersonResponse, PersonRequest> {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private IRepository<Person> personRepository;

    @Autowired
    private IRepository<House> houseRepository;

    @Override
    public List<PersonResponse> getAll() {
        return personRepository.findAll().stream()
                .map(personMapper::toPersonDTO)
                .toList();
    }

    @Override
    public PersonResponse get(UUID uuid) {
        return personRepository.findByUuid(uuid)
                .map(personMapper::toPersonDTO)
                .orElseThrow(() -> EntityNotFoundException.of(Person.class, uuid));
    }

    @Override
    @Transactional
    public UUID create(PersonRequest entity) {
        Person personToSave = personMapper.toPerson(entity);
        personToSave.setLiveHouse(getLiveHouse(entity));
        return personRepository.save(personToSave);
    }

    @Override
    @Transactional
    public void update(UUID uuid, PersonRequest entity) {
        Person personToUpdate = personRepository.findByUuid(uuid)
                .orElseThrow(() -> EntityNotFoundException.of(Person.class, uuid));
        personToUpdate.setUpdateDate(LocalDateTime.now());
        if (!entity.getUuidHouse().equals(personToUpdate.getLiveHouse().getUuid())) {
            personToUpdate.setLiveHouse(getLiveHouse(entity));
        }
        personRepository.update(personMapper.update(personToUpdate, entity));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        personRepository.delete(uuid);
    }

    @Override
    public List<HouseResponse> getOwningHouses(UUID uuid) {
        return personRepository.findByUuid(uuid)
                .orElseThrow(() -> EntityNotFoundException.of(Person.class, uuid))
                .getOwnHouses().stream()
                .map(houseMapper::toHouseDTO)
                .toList();
    }

    private House getLiveHouse(PersonRequest entity) {
        UUID liveHouseUuid = entity.getUuidHouse();
        return houseRepository.findByUuid(liveHouseUuid)
                .orElseThrow(() -> EntityNotFoundException.of(House.class, liveHouseUuid));
    }


}
