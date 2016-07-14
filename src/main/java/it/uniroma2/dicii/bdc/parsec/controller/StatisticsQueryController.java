package it.uniroma2.dicii.bdc.parsec.controller;

import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StatisticsQueryController {


    public StatisticsQueryController() {
    }

    /**
     * @param redshift    value on which search
     * @param searchLower It indicates whether we have to search for all values less than specified one
     * @return list of {@link Galaxy} ordered by redshift value
     */
    public List<Galaxy> searchForRedshift(float redshift, boolean searchLower) {

        if (searchLower)
            // find al Galaxies that have a
            // redshift value lower than specified one
            return GalaxyDAO.findLower(redshift);
        else
            // find al Galaxies that have a
            // redshift value greater than specified one
            return GalaxyDAO.findGreater(redshift);
    }

    /**
     * Given a spatial position it finds n-nearest galaxies
     *
     * @param ascension   describes position {@link Position#ascension}
     * @param declination describes position {@link Position#declination}
     * @param howMany     Indicates how many galaxies must be returned
     * @return list of n-galaxies nearest the given position
     */
    public List<Galaxy> searchInRange(Ascension ascension, Declination declination, Integer howMany) {

        List<Galaxy> galaxies;

        try {
            // database query
            galaxies = GalaxyDAO.findInRange(ascension, declination, howMany);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        // return null if exception occurs
        return galaxies;
    }

    /**
     * @param fluxLine: {@link Flux#atom}
     * @param galaxy    {@link Galaxy#name}
     * @return An HashMap that contains all division between flux's value and the corresponding continuous flux.
     * The key of the HashMap represents the resolution of the flux line.
     */
    public HashMap<String, Double> calculateRatio(String fluxLine, String galaxy) {

        //Contains all results from the division of the flux's value and the continuous flux.
        // The Key of the hashmap is the resolution of the flux line (3x3 , 5x5 , c )
        HashMap<String, Double> results;


        try {
            // db query
            results = FluxDAO.ratioFluxContinuous(fluxLine, galaxy); // all flux not continuous
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // return null if exception occurs
        return results;
    }

    /**
     * Calculate kind of statistics operation among average value, median value,
     * standard deviation, absolute standard deviation of al ratio between values
     * of galaxy.
     *
     * @param query user query containg type of operation, category and eventually aperture
     * @return result of statistical operation
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Double calculateStatistics(QueryBoundary query) throws SQLException, ClassNotFoundException {

        if (query.getResolution() == null || query.getResolution().length() == 0) {
            if (query.getOperation().compareTo("med") == 0) {
                return medianLinesRatioValues(query.getCategory());
            } else if (query.getOperation().compareTo("avg") == 0) {
                return averageLinesRatioValues(query.getCategory());
            } else if (query.getOperation().compareTo("std") == 0) {
                return standarDeviationLinesRatioValues(query.getCategory());
            } else if (query.getOperation().compareTo("astd") == 0) {
                return averageAbsoluteDeviationLinesRatioValues(query.getCategory());
            }
        } else {
            if (query.getOperation().compareTo("med") == 0) {
                return medianLinesRatioValuesByAperture(query.getCategory(), query.getResolution());
            } else if (query.getOperation().compareTo("avg") == 0) {
                return averageLinesRatioValuesByAperture(query.getCategory(), query.getResolution());
            } else if (query.getOperation().compareTo("std") == 0) {
                return standarDeviationLinesRatioValuesByAperture(query.getCategory(), query.getResolution());
            } else if (query.getOperation().compareTo("astd") == 0) {
                return averageAbsoluteDeviationLinesRatioValuesByAperture(query.getCategory(), query.getResolution());
            }
        }
        return null;
    }

    private Double averageLinesRatioValuesByAperture(String category, String aperture) {

        try {
            return FluxDAO.findAverageFluxLinesRatioByCategoryAndAperture(category, aperture);
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

    private Double averageLinesRatioValues(String category) throws SQLException, ClassNotFoundException {

        try {
            return FluxDAO.findAverageFluxLinesRatioByCategory(category);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Double medianLinesRatioValuesByAperture(String category, String aperture) {

        try {
            return FluxDAO.findAllFluxLinesMedianRatioByCategoryAndAperture(category, aperture);

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

    private Double medianLinesRatioValues(String category) throws SQLException, ClassNotFoundException {

        try {
            return FluxDAO.findAllFluxLinesMedianRatioByCategory(category);
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Double averageAbsoluteDeviationLinesRatioValuesByAperture(String category, String aperture) {

        try {
            return 0.6475d * FluxDAO.findAllFluxLinesDeviationByCategory(category);

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

    private Double averageAbsoluteDeviationLinesRatioValues(String category) {

        try {
            return 0.6475d * FluxDAO.findAllFluxLinesDeviationByCategory(category);
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

    private Double standarDeviationLinesRatioValuesByAperture(String category, String aperture) {

        try {
            return 0.6475d * FluxDAO.findAllFluxLinesDeviationByCategoryAndAperture(category, aperture);

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

    private Double standarDeviationLinesRatioValues(String category) {

        try {
            return 0.6475d * FluxDAO.findAllFluxLinesDeviationByCategory(category);
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
}