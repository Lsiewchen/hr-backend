package Practicum2;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * Application configuration class for declaring root resource and provider classes.
 * Defines the base URI for all REST endpoints using @ApplicationPath.
 */
@ApplicationPath("/api")
public class HRApp extends Application {
    /**
     * Registers the REST resource classes used by this application.
     * @return - a set containing all resource classes to be exposed as REST endpoints
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(HRResource.class);
        return s;
    }
}
