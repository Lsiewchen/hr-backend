package Practicum2;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class Driver {
    static final String DBNAME = "employees";

    public static void main(String[] args){
        Map<String,String> persistenceMap = new HashMap<>();
        persistenceMap.put("jakarta.persistence.jdbc.url",
                "jdbc:mariadb://localhost:3306/"+DBNAME);

//        try (EMFactory emf = EMFactory.getInstance(
//                "HRService", persistenceMap)) {
//
//        }
    }
}
