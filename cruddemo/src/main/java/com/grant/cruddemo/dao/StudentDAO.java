package com.grant.cruddemo.dao;

import com.grant.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Marks this as a DAO component
public class StudentDAO implements IStudentDAO{
    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    // implement save method

    // veritabanı işlemleri için bir işlem (transaction) bağlamında çalıştırılmasını sağlar
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query -- jpa entity icin -- yani veritabanı degil @entity'de ne verdin o onemli oyle mapledin cunku
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName asc", Student.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "From Student WHERE lastName=:theData", Student.class);

        // set query parameter
        theQuery.setParameter("theData",theLastName);

        // return the query result
        return theQuery.getResultList();
    }

    @Override
    @Transactional  // entity ile işlem yapıcak ondan bu gerekli
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("Delete FROM Student").executeUpdate();

        return numRowsDeleted;
    }
}
