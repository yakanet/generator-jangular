package <%= packageName %>.provider;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProvider {

	@PersistenceContext
    private EntityManager entityManager;

	@Produces
	@RequestScoped
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
