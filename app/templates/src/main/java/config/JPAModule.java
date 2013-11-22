package <%= packageName %>.config;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

import <%= packageName %>.dao.api.UserDao;
import <%= packageName %>.dao.impl.UserJPADao;

public class JPAModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(UserDao.class).to(UserJPADao.class);
        
        //
        install(new JpaPersistModule("<%= _.camelize(baseName) %>JpaUnit"));  // like we saw earlier.
        filter("/*").through(PersistFilter.class);
    }
}
