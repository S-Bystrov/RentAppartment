package com.bystrov.rent.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class EntityDAO<T> {
    private Class <T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    protected final void setEntityClass( Class <T> entityClassToSet){
        this.entityClass = entityClassToSet;
    }

    public T findById(Long id) {
        return em.find(entityClass, id);
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public void deleteById(Long id) {
        T entity = findById(id);
        em.remove(entity);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public T findByLogin(String login) {
        return em.find(entityClass, login);
    }
}
