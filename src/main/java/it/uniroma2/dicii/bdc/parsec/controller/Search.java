package it.uniroma2.dicii.bdc.parsec.controller;

import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.Position;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

/**
 *
 */
public class Search {

    /**
     * Default constructor
     */
    public Search() {
    }


    /**
     *
     */
    public void importFile() {
        // TODO implement here
    }

    /**
     * Query to visualize position, distance and redshift value, luminosity, metallicity
     * and relative errors
     *
     * @param galaxyName name of galaxy to search info about
     * @return {@link Galaxy}
     */
    public Galaxy searchGalaxyByName(String galaxyName) {

        try {
            return GalaxyDAO.findByName(galaxyName);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param range
     * @param position
     * @return
     */
    public List<Galaxy> searchGalaxies(Position position, Float range) {
        // TODO implement here
        return null;
    }

    /**
     * @param readshift
     */
    public void searchGalaxies(Float readshift) {
        // TODO implement here
    }

    /**
     * @param atom
     * @return
     */
    public List<Flux> fluxForLine( String atom) {
        // TODO implement here
        return null;
    }

}