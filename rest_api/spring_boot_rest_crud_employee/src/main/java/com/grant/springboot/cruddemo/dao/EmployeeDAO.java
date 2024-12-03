package com.grant.springboot.cruddemo.dao;

import com.grant.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO implements IEmployeeDAO{
    private EntityManager entityManager;


    @Autowired
    public EmployeeDAO(EntityManager entityManager){
        // entitymanager spring boot tarafından üretilir zaten
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);

        return employee;
    }

    // @Transactinol kullanılmadı service'de olcak o
    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);

        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        /*
        * Bir nesneyi kalıcı hale getirir veya mevcut bir kaydı günceller.
        * Eğer nesne daha önce veritabanında kaydedilmişse, merge metodu bu nesneyi günceller.
        * Eğer nesne veritabanında yoksa, merge metodu bu nesneyi veritabanına yeni bir kayıt olarak ekler.
        * */
        Employee employee = entityManager.find(Employee.class, id);

        entityManager.remove(employee);
    }
}

/*
* @Repository bir Spring anotasyonudur ve bu sınıfın bir veri erişim katmanı (DAO) sınıfı olduğunu belirtir.
* Bu sınıfın, veritabanıyla etkileşim kurmak için tasarlanmış olduğunu işaret eder.
* Spring, @Repository ile işaretlenmiş sınıfları otomatik olarak algılar ve uygulama bağlamına (application context) bir bean olarak ekler.
*
* @Autowired, Spring'in dependency injection (bağımlılık enjeksiyonu) mekanizmasını kullanarak bir bileşeni (bean) otomatik olarak sağlayacağını ifade eder.
* Bu anotasyon, bir sınıfın ihtiyacı olan bağımlılıkların otomatik olarak ayarlanmasını sağlar.
*
* EntityManager, JPA (Java Persistence API) tarafından sağlanan bir arayüzdür. Veritabanı işlemlerini (CRUD operasyonlarını) gerçekleştirir.
* */