package ru.clevertec.house.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.EntityNotFoundException;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.repository.impl.PersonRepository;
import ru.clevertec.house.service.IService;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService implements IService<PersonResponse, PersonRequest> {

    @Autowired
    private PersonMapper mapper;
    @Autowired
    private PersonRepository repository;

    @Override
    public List<PersonResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toPersonDTO)
                .toList();
    }

    @Override
    public PersonResponse get(UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::toPersonDTO)
                .orElseThrow(() -> EntityNotFoundException.of(Person.class, uuid));
    }

    @Override
    @Transactional
    public UUID create(PersonRequest entity) {
        return repository.save(mapper.toPerson(entity));
    }

    @Override
    @Transactional
    public void update(UUID uuid, PersonRequest entity) {
        Person personToUpdate = repository.findByUuid(uuid)
                .orElseThrow(() -> EntityNotFoundException.of(Person.class, uuid));
        repository.update(mapper.update(personToUpdate, entity));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        repository.delete(uuid);
    }
}
