package it.uniroma2.dicii.bdc.parsec.controller;


import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class to manage interaction between beans and database to elaborate queries.
 *
 */
public class SearchQueryController {

    public SearchQueryController() {
    }

    /**
     * Calculation of value of ratio between two line flux values
     * specified into boundary for this query
     * @param query query containing name of galaxy and two lines
     * @return a tuple as result of query with: value of ratio,
     * indication of upperlimit for numerator value, indication of upperlimit for
     * denumerator value
     */
    public List<String> calculateRatio(QueryBoundary query) {

        try {
            List<String> list = FluxDAO.findRatioBetweenTwoLinesFluxValues(
                    query.getGalaxyName(), query.getFluxNum(), query.getFluxDen());
            System.out.printf("%s",list);
            return list;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Query to visualize position, distance and redshift value, luminosity, metallicity
     * and relative errors
     *
     * @param query object with name of galaxy to search info about
     * @return {@link Galaxy}
     */
    public List<List<String>> searchGalaxyDescriptionByName(QueryBoundary query)
            throws IOException, SQLException, ClassNotFoundException {

        try {
            return GalaxyDAO.findDescriptionByName(query.getGalaxyName());
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Query to visualize all values for fluxes for lines of a specified galaxy.
     *
     * @param query object with name of galaxy to search info about
     * @return list of tuple for query requested
     */
    public List<List<String>> searchFluxLinesValuesByGalaxy(QueryBoundary query)
            throws IOException, SQLException, ClassNotFoundException {

        try {
            return FluxDAO.findFluxLinesValuesByGalaxy(query.getGalaxyName());
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
