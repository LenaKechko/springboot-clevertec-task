package ru.clevertec.house.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.House;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)//, expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
    House toHouse(HouseRequest houseDto);

    HouseResponse toHouseResponse(House house);

    House update(@MappingTarget House house, HouseRequest houseDto);
}