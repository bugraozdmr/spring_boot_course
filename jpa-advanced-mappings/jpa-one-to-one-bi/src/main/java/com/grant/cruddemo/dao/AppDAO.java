package com.grant.cruddemo.dao;

import com.grant.cruddemo.entity.Instructor;
import com.grant.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
