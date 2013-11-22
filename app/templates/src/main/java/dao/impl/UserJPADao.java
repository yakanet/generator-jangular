package <%= packageName %>.dao.impl;

import <%= packageName %>.dao.api.UserDao;
import <%= packageName %>.entity.User;

public class UserJPADao extends GenericJPADao<User, Long> implements UserDao {

    @Override
    public User findByUsername(String username) {
        String queryString = "FROM User u WHERE u.username = :username";
        try {
            return (User) getEntityManager().createQuery(queryString)//
                    .setParameter("username", username)//
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
