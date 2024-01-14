package ru.clevertec.house.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.EntityNotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.repository.HouseRepository;
import ru.clevertec.house.service.HouseService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class HouseServiceImpl extends HouseService<HouseResponse, HouseRequest> {

    @Autowired
    private HouseRepository<House> houseRepository;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private PersonMapper personMapper;

    public List<HouseResponse> getAll() {
        return houseRepository.findAll().stream()
                .map(houseMapper::toHouseDTO)
                .toList();
    }

    @Override
    public HouseResponse get(UUID uuid) {
        return houseRepository.findByUuid(uuid)
                .map(houseMapper::toHouseDTO)
                .orElseThrow(() -> EntityNotFoundException.of(House.class, uuid));
    }

    @Override
    public UUID create(HouseRequest entity) {
        return houseRepository.save(houseMapper.toHouse(entity));
    }

    @Override
    public void update(UUID uuid, HouseRequest entity) {
        House houseToUpdate = houseRepository.findByUuid(uuid)
                .orElseThrow(() -> EntityNotFoundException.of(House.class, uuid));
        houseRepository.update(houseMapper.update(houseToUpdate, entity));
    }

    @Override
    public void delete(UUID uuid) {
        houseRepository.delete(uuid);
    }

    public List<PersonResponse> getPersonsLivingInHouse(UUID uuid) {
        return houseRepository.findPersonsLivingInHouse(uuid).stream()
                .map(personMapper::toPersonDTO)
                .toList();
    }

}
