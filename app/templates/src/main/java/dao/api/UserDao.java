package <%= packageName %>.dao.api;

import <%= packageName %>.entity.User;

public interface UserDao extends GenericDao<User, Long>{

    User findByUsername(String username);

}
