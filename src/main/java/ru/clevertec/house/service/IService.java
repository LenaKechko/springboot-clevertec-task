package ru.clevertec.house.service;

import java.util.List;
import java.util.UUID;

public interface IService<T, K> {

    List<T> getAll();

    T get(UUID uuid);

    UUID create(K entity);

    void update(UUID uuid, K entity);

    void delete(UUID uuid);
}
