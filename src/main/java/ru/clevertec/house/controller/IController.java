package ru.clevertec.house.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IController<T, K> {
    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> get(UUID uuid);

    ResponseEntity<UUID> create(K entity);

    void update(UUID uuid, K entity);

    void delete(UUID uuid);
}
