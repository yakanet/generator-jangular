package <%= packageName %>.dao.api;

import java.util.List;

public interface GenericDao<TYPE, ID> {
    /**
     * <p>
     * Get the entity with the specified type and id from the datastore.
     * 
     * <p>
     * If none is found, return null.
     */
    public TYPE find(ID id);
    
    
    /**
     * If an entity with the same ID already exists in the database, merge the
     * changes into that entity. If not persist the given entity. In either
     * case, a managed entity with the changed values is returned. It may or may
     * not be the same object as was passed in.
     */
    public TYPE persist(TYPE entity);


    List<TYPE> findAll();


    void merge(TYPE entity);


    void delete(TYPE entity);


    void refresh(TYPE entity);
    
    
}
