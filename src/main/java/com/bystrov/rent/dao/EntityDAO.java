package com.bystrov.rent.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class EntityDAO<T> {
    private Class <T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    protected final void setEntityClass( Class <T> entityClassToSet){
        this.entityClass = entityClassToSet;
    }

    T findById(Long id){
        return em.find(entityClass, id);
    }

    void save(T entity){
        em.persist(entity);
    }

    void delete(T entity){
        em.remove(entity);
    }

    void deleteById(Long id){
        T entity = findById(id);
        em.remove(entity);
    }

    List<T> getAll(){
        return em.createQuery("from" + entityClass.getName()).getResultList();
    }

    T update(T entity){
        return em.merge(entity);
    }

    T findByLogin(String login){ return em.find(entityClass, login); }
}
