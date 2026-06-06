package org.example.repository;

import org.example.model.Booking;
import org.example.model.User;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BookingRepository {

    public void save(Booking booking) {
        try(Session session =
                    HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.persist(booking);
            session.getTransaction().commit();
        }
    }

    public List<Booking> findByUser(User user) {
        try(Session session =
                    HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "from Booking where user = :u",
                            Booking.class)
                    .setParameter("u", user)
                    .list();
        }
    }

    public void update(Booking booking) {
        try(Session session =
                    HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.merge(booking);
            session.getTransaction().commit();
        }
    }


    public Booking findById(int id){

        try(Session session =
                    HibernateUtil
                            .getSessionFactory()
                            .openSession()){

            return session.get(
                    Booking.class,
                    id
            );
        }
    }

    public void delete(Booking booking){

        try(Session session =
                    HibernateUtil
                            .getSessionFactory()
                            .openSession()){

            session.beginTransaction();

            session.remove(
                    session.contains(booking)
                            ? booking
                            : session.merge(booking)
            );

            session.getTransaction().commit();
        }
    }


}