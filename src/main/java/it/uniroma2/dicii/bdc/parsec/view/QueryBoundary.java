package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.Search;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.EntityManager;
import java.util.*;

/**
 * 
 */
public class QueryBoundary {

    private String galaxyName;

    /**
     * Default constructor
     */
    public QueryBoundary() {
    }

    public String getGalaxyName() {
        return galaxyName;
    }

    public void setGalaxyName(String galaxyName) {
        this.galaxyName = galaxyName;
    }

    public ResultsBean getGalaxyByName() {

        if (galaxyName == null)
            return null;

        Search controller = new Search();

        Galaxy galaxy = controller.searchGalaxyByName(galaxyName);

        if (galaxy == null) {
            return null;
        }

         return new ResultsBean(galaxy);
    }


}