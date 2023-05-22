package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getByModel(String model, int series) throws NoResultException {
        String hql = "SELECT u FROM User u JOIN FETCH u.mCar c WHERE c.model = :model AND c.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model);
        query.setParameter("series", series);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("User not found with model " + model + " and series " + series);
        }
    }

    public void deleteAllCars() {
        Session entityManager;
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM Car");
        query.executeUpdate();
    }

    public void deleteAllUser() {
        Session entityManager;
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User");
        query.executeUpdate();
    }
}


