package com.bystrov.rent.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    public void deleteById(Long id) {
        T entity = findById(id);
        em.remove(entity);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public T findByUsername(String username) {
        Query query = em.createQuery("FROM User U WHERE U.username = :paramUsername");
        query.setParameter("paramUsername", username);
        List<T> result = (List<T>) query.getResultList();
        if(result.size() != 0){
            return result.get(0);
        }
        return null;
    }

    public T findByActivationCode(String code) {
        Query query = em.createQuery("FROM User U WHERE U.activationCode = :paramCode");
        query.setParameter("paramCode", code);
        List<T> result = (List<T>) query.getResultList();
        if(result.size() != 0){
            return result.get(0);
        }
        return null;
    }

    public T findByEmail(String email) {
        Query query = em.createQuery("FROM User U WHERE U.email = :paramEmail");
        query.setParameter("paramEmail", email);
        List<T> result = (List<T>) query.getResultList();
        if(result.size() != 0){
            return result.get(0);
        }
        return null;
    }

    public List<T> findAll(){
        Query query = em.createQuery("FROM " + entityClass.getName());
        List<T> result = (List<T>) query.getResultList();
        if(result.size() == 0){
            return null;
        }
        return result;
    }

    public List<T> findAllByUserId(Long idUser){
        Query query = em.createQuery("FROM " + entityClass.getName() +
                " WHERE ID_USER = :paramUserId");
        query.setParameter("paramUserId", idUser);
        List<T> result = (List<T>) query.getResultList();
        if(result.size() == 0) {
            return null;
        }
        return result;
    }

}
