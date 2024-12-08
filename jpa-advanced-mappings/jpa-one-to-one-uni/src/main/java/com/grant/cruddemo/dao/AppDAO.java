package com.grant.cruddemo.dao;

import com.grant.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
}
