package ru.clevertec.house.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.house.controller.IController;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.EntityNotFoundException;
import ru.clevertec.house.service.impl.PersonService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController implements IController<PersonResponse, PersonRequest> {

    @Autowired
    private PersonService service;

    @Override
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<PersonResponse> get(@PathVariable UUID uuid) {
        try {
            return ResponseEntity.ok(service.get(uuid));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    @Override
    @PostMapping
    public UUID create(@RequestBody PersonRequest entity) {
        return service.create(entity);
    }

    @Override
    @PutMapping("/{uuid}")
    public void update(@PathVariable UUID uuid,
                       @RequestBody PersonRequest entity) {
        try {
            service.update(uuid, entity);
//            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    @Override
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        service.delete(uuid);
    }

    @GetMapping("/{uuid}/owners")
    public List<House> getPersonOwningHouse(@PathVariable UUID uuid) {
        return null;
    }
}
