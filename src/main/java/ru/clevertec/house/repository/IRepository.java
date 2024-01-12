package ru.clevertec.house.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRepository<T> {
    List<T> findAll();

    Optional<T> findByUuid(UUID uuid);

    UUID save(T entity);

    void update(T entity);

    void delete(UUID uuid);
}
