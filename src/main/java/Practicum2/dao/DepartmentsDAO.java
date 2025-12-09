package Practicum2.dao;

import Practicum2.EMFactory;
import Practicum2.entities.Departments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * DAO class that encapsulates all database access and operations for Departments data.
 */
public class DepartmentsDAO {
    /**
     * Entity Manager Factory instance to be used to create the Entity Manager.
     */
    private EntityManagerFactory emf;

    /**
     * Constructor that retrieves the shared EntityManagerFactory instance.
     */
    public DepartmentsDAO() {
        this.emf = EMFactory.getInstance();
    }

    /**
     * Method to get all departments.
     * @return - a list of all departments
     */
    public List<Departments> findAllDepartments() {
        List<Departments> departments;
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            departments = em.createNamedQuery(
                    "Departments.findAll", Departments.class).getResultList();
            em.getTransaction().commit();
        }
        return departments;
    }
}
