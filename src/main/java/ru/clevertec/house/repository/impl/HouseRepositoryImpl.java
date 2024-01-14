package ru.clevertec.house.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.EntityNotFoundException;
import ru.clevertec.house.repository.HouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HouseRepositoryImpl extends HouseRepository<House> {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    public List<House> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from House", House.class)
                .setFirstResult(0)
                .setMaxResults(15)
                .getResultList();
    }

    @Override
    public Optional<House> findByUuid(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM House h WHERE h.uuid = :uuidParam", House.class)
                .setParameter("uuidParam", uuid)
                .uniqueResultOptional();
    }

    @Override
    public UUID save(House house) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(house);
        return house.getUuid();
    }

    @Override
    public void update(House house) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(house);
    }

    @Override
    public void delete(UUID uuid) {
        //        String sql = "SELECT id, uuid, area, country, city, street, \"number\", create_date FROM houses";
//        List<House> houses = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(House.class));

        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM House h WHERE h.uuid = :uuidParam")
                .setParameter("uuidParam", uuid)
                .executeUpdate();
    }

    @Override
    public List<Person> findPersonsLivingInHouse(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        House house = findByUuid(uuid)
                .orElseThrow(() -> EntityNotFoundException.of(House.class, uuid));
        return house.getResidents();
    }

}
