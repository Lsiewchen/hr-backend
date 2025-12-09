package Practicum2;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;

/**
 * The EMFactory class is used to manage the
 * {@link jakarta.persistence.EntityManagerFactory} object as a Singleton instance.
 */
public class EMFactory {

    /**
     * A static singleton instance of {@link EntityManagerFactory}
     */
    private static volatile EntityManagerFactory emf;

    /**
     * The name of the persistence unit used for creating an
     * EntityManagerFactory instance.
     */
    private static final String persistenceUnitName = "HRService";

    /**
     * The name of the database used in the application. This application
     * uses the employees database loaded from
     * <a href="https://dev.mysql.com/doc/employee/en/">dev.mysql.com</a>
     */
    private static final String DBName = "employees";

    /**
     * The base URL for connecting to the database. This application connects
     * to MariaDB on the default port of 3306.
     */
    private static final String DBUrl = "jdbc:mariadb://localhost:3306/";

    /**
     * Private constructor class used to instantiate the EMFactory class.
     * Only accessible using the getInstance() method to enforce the
     * Singleton design pattern.
     */
    private EMFactory(){
        emf = Persistence.createEntityManagerFactory(
                persistenceUnitName, Map.of("jakarta.persistence.jdbc.url",
                        DBUrl + DBName));
    }

    /**
     * Main method used to return the singleton instance of
     * {@link EntityManagerFactory} or create a new
     * {@link EntityManagerFactory} instance if it does not exist yet.
     * @return the singleton instance of {@link EntityManagerFactory}
     */
    public static EntityManagerFactory getInstance(){
        if (emf == null){
            synchronized (EMFactory.class) {
                if (emf == null) {
                    new EMFactory();
                }
            }
        }
        return emf;
    }

    /**
     * Closes the existing {@link EntityManagerFactory} instance.
     * This method is used to release any resources held by the
     * EntityManagerFactory.
     */
    public static void closeEMF() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
