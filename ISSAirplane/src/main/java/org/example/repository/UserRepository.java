package org.example.repository;

import org.example.model.User;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

public class UserRepository {

    public User findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from User where username = :u", User.class)
                    .setParameter("u", username)
                    .uniqueResult();
        }    }

    public void save(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }
}
