package ru.clevertec.house.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.repository.IRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HouseRepository implements IRepository<House> {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    public List<House> findAll() {
//        String sql = "SELECT id, uuid, area, country, city, street, \"number\", create_date FROM houses";
//        List<House> houses = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(House.class));

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from House", House.class)
                .setFirstResult(0)
                .setMaxResults(15)
                .getResultList();
    }

    @Override
    public Optional<House> findByUuid(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM house as h WHERE uuid=" + uuid;
        return Optional.ofNullable(session.createSelectionQuery(hql, House.class).getSingleResult());
//        return Optional.ofNullable(
//                (House) session.createQuery("from House H where H.uuid = :paramUuid").setParameter("paramUuid", uuid).getSingleResult());
    }

    @Override
    public UUID save(House house) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("INSERT INTO House (uuid, area, country, city, street, number, create_date) " +
                        "select h.uuid, h.area, h.country, h.city, h.street, h.numberHouse, h.createDate from House h", House.class)
                .executeUpdate();
        return house.getUuid();
    }

    @Override
    public void update(House house) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update House "
                + "SET area = :areaParam "
                + ", country = :countryParam "
                + ", city = :cityParam "
                + ", street = :streetParam "
                + ", numberHouse = :numberHouseParam "
                + " where uuid = :uuidParam";

        session.createQuery(hql, House.class)
                .setParameter("areaParam", house.getArea())
                .setParameter("countryParam", house.getCountry())
                .setParameter("cityParam", house.getCity())
                .setParameter("streetParam", house.getStreet())
                .setParameter("numberHouseParam", house.getNumberHouse())
                .setParameter("uuidParam", house.getUuid())
                .executeUpdate();
    }

    @Override
    public void delete(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE House WHERE uuid = :uuidParam";

        session.createQuery(hql, House.class)
                .setParameter("uuidParam", uuid)
                .executeUpdate();
    }

}
