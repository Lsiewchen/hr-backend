package Practicum2.dao;

import Practicum2.EMFactory;
import Practicum2.entities.Departments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class DepartmentsDAO {
    private EntityManagerFactory emf;

    public DepartmentsDAO() {
        this.emf = EMFactory.getInstance();
    }

    public List<Departments> findAllDepartments() {
        List<Departments> departments;
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            departments = em.createNamedQuery(
                    "Departments.findAll", Departments.class).getResultList();
        }
        return departments;
    }
}
