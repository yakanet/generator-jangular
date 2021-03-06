package <%= packageName %>.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import <%= packageName %>.dao.api.GenericDao;

@Stateful
public class GenericJPADao<TYPE, ID> implements GenericDao<TYPE, ID> {

    private Class<TYPE> targetClass;
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericJPADao() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();

        if (types[0] instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) types[0];
            targetClass = (Class<TYPE>) type.getRawType();
        } else {
            targetClass = (Class<TYPE>) types[0];
        }
    }

    @Override
    public TYPE find(ID id) {
        return entityManager.find(targetClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TYPE> findAll() {
        return entityManager.createQuery("SELECT x FROM " + targetClass.getSimpleName() + " x").getResultList();
    }

    @Override
    public TYPE persist(TYPE entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void merge(TYPE entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(TYPE entity) {
        entityManager.remove(entity);
    }

    @Override
    public void refresh(final TYPE entity) {
        entityManager.refresh(entity);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
