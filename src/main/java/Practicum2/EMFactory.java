package Practicum2;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;

public class EMFactory {
    private static EMFactory emFactory;
    private static EntityManagerFactory emf;
    private static final String persistenceUnitName = "HRService";
    private static final String DBName = "employees";
    private static final String DBUrl = "jdbc:mariadb://localhost:3306/";

    private EMFactory(){
        emf = Persistence.createEntityManagerFactory(
                persistenceUnitName, Map.of("jakarta.persistence.jdbc.url",
                        DBUrl + DBName));
    }

    public static EntityManagerFactory getInstance(){
        if (emFactory == null){
            synchronized (EMFactory.class) {
                if (emFactory == null) {
                    emFactory = new EMFactory();
                }
            }
        }
        return emf;
    }

    public static void closeEMF() {
        emf.close();
    }
}
