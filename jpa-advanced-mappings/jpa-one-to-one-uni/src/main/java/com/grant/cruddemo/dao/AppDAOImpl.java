package com.grant.cruddemo.dao;

import com.grant.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        // cascade sayesinde iki save olcak
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        entityManager.remove(tempInstructor);
    }
}
