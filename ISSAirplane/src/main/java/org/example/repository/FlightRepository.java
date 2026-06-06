package org.example.repository;


import org.example.model.Flight;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class FlightRepository {

    public List<Flight> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Flight", Flight.class).list();
        }
    }

    public Flight findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Flight.class, id);
        }
    }

    public void save(Flight f) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(f);
            session.getTransaction().commit();
        }
    }

    public void update(Flight flight) {
        try(Session session =
                    HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.merge(flight);
            session.getTransaction().commit();
        }
    }

    public void delete(
            Flight flight){

        try(Session session=
                    HibernateUtil
                            .getSessionFactory()
                            .openSession()){

            session.beginTransaction();

            session.remove(
                    session.contains(flight)
                            ? flight
                            : session.merge(flight)
            );

            session.getTransaction()
                    .commit();
        }
    }

}