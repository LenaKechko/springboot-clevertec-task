package ru.clevertec.house.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.repository.IRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepository implements IRepository<Person> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person", Person.class)
                .setFirstResult(0)
                .setMaxResults(15)
                .getResultList();
    }

    @Override
    public Optional<Person> findByUuid(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM person as h WHERE uuid=" + uuid;
        return Optional.ofNullable(session.createSelectionQuery(hql, Person.class).getSingleResult());
    }

    @Override
    public UUID save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("INSERT INTO Person (uuid, name, surname, sex, passport_series, passport_number, create_date, update_date, id_live_house) " +
                        "select p.uuid, p.name, p.surname, p.sex, p.passport.passportSeries, p.passport.passportNumber, p.createDate, p.updateDate, p.liveHouse.id from Person p", Person.class)
                .executeUpdate();
        return person.getUuid();
    }

    @Override
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update Person "
                + "SET name = :nameParam "
                + ", surname = :surnameParam "
                + ", sex = :sexParam "
                + ", passport.passportSeries = :passportSeriesParam "
                + ", passport.passportNumber = :passportNumberParam "
                + ", updateDate = :updateDateParam "
                + " where uuid = :uuidParam";

        session.createQuery(hql, Person.class)
                .setParameter("nameParam", person.getName())
                .setParameter("surnameParam", person.getSurname())
                .setParameter("sexParam", person.getSex())
                .setParameter("passportSeriesParam", person.getPassport().getPassportSeries())
                .setParameter("passportNumberParam", person.getPassport().getPassportNumber())
                .setParameter("updateDateParam", person.getUpdateDate())
                .setParameter("uuidParam", person.getUuid())
                .executeUpdate();
    }

    @Override
    public void delete(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE Person WHERE uuid = :uuidParam";

        session.createQuery(hql, Person.class)
                .setParameter("uuidParam", uuid)
                .executeUpdate();
    }
}
