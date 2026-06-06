package org.example.repository;

import org.example.model.Airport;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AirportRepository {

    public List<Airport>
    findAll(){

        try(Session session=
                    HibernateUtil
                            .getSessionFactory()
                            .openSession()){

            return session
                    .createQuery(
                            "from Airport",
                            Airport.class
                    )
                    .list();
        }
    }

    public void save(
            Airport airport){

        try(Session session=
                    HibernateUtil
                            .getSessionFactory()
                            .openSession()){

            session.beginTransaction();

            session.persist(
                    airport
            );

            session.getTransaction()
                    .commit();
        }

    }

    public void update(
            Airport airport){

        try(Session session=
                    HibernateUtil
                            .getSessionFactory()
                            .openSession()){

            session.beginTransaction();

            session.merge(
                    airport
            );

            session.getTransaction()
                    .commit();
        }

    }

    public void delete(
            Airport airport){

        try(Session session=
                    HibernateUtil
                            .getSessionFactory()
                            .openSession()){

            session.beginTransaction();

            session.remove(
                    session.contains(
                            airport
                    )
                            ? airport
                            : session.merge(
                            airport
                    )
            );

            session.getTransaction()
                    .commit();
        }
    }

}