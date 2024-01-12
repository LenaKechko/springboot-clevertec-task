package ru.clevertec.house.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.EntityNotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.repository.impl.HouseRepository;
import ru.clevertec.house.service.IService;

import java.util.List;
import java.util.UUID;

@Service
public class HouseService implements IService<HouseResponse, HouseRequest> {

    @Autowired
    private HouseRepository repository;

    @Autowired
    private HouseMapper mapper;

    public List<HouseResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toHouseDTO)
                .toList();
    }

    @Override
    public HouseResponse get(UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::toHouseDTO)
                .orElseThrow(() -> EntityNotFoundException.of(House.class, uuid));
    }

    @Override
    @Transactional
    public UUID create(HouseRequest entity) {
        return repository.save(mapper.toHouse(entity));
    }

    @Override
    @Transactional
    public void update(UUID uuid, HouseRequest entity) {
        House houseToUpdate = repository.findByUuid(uuid)
                .orElseThrow(() -> EntityNotFoundException.of(House.class, uuid));
        repository.update(mapper.update(houseToUpdate, entity));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        repository.delete(uuid);
    }
}
