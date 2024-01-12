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
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.EntityNotFoundException;
import ru.clevertec.house.service.impl.HouseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/houses")
public class HouseController implements IController<HouseResponse, HouseRequest> {

    @Autowired
    private HouseService service;
//    @Autowired
//    private ObjectMapper mapper;

//    @Autowired
//    private HttpHeaders httpHeaders;

    @GetMapping
    public ResponseEntity<List<HouseResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
//        return new ResponseEntity<>(mapper.writeValueAsString(service.getAll()),
//                httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<HouseResponse> get(@PathVariable UUID uuid) {
        try {
            return ResponseEntity.ok(service.get(uuid));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    @PostMapping
    public UUID create(@RequestBody HouseRequest entity) {
        return service.create(entity);
    }

    @PutMapping("/{uuid}")
    public void update(@PathVariable UUID uuid,
                       @RequestBody HouseRequest entity) {
        try {
            service.update(uuid, entity);
//            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        service.delete(uuid);
    }

    @GetMapping("/{uuid}/residents")
    public List<Person> getPersonLivingInHouse(@PathVariable UUID uuid) {
        return null;
    }

    @GetMapping("/{uuid}/owners")
    public List<Person> getPersonOwningHouse(@PathVariable UUID uuid) {
        return null;
    }
}
