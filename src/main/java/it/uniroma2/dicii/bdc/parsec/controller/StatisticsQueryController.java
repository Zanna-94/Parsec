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

    public List<Galaxy> searchInRange(Ascension ascension, Declination declination, Integer howMany) {

        List<Galaxy> galaxies;

        try {
            galaxies = GalaxyDAO.findInRange(ascension, declination, howMany);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

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
            results = FluxDAO.ratioFluxContinuous(fluxLine, galaxy); // all flux not continuous
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return results;
    }

    /**
     * Query to visualize position, distance and redshift value, luminosity, metallicity
     * and relative errors
     *
     * @return {@link Galaxy}
     */
    /*public Galaxy searchGalaxyByName(String galaxyName) {

        try {
            return GalaxyDAO.findByName(galaxyName);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    public Double calculateStatistics(QueryBoundary query) throws SQLException, ClassNotFoundException {

        if (query.getResolution() == null && query.getResolution().length() == 0) {
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

    private List<Double> linesRatioValues(List<Double> f) {

        List<Double> list = new ArrayList<Double>();
        Integer n = f.size();
        Integer i = 0, j, k;
        while (i < n) {
            j = i;
            while (j >= 0) {
                if (!i.equals(j) && !f.get(j).equals(0d))
                    list.add(f.get(i) / f.get(j));
                j--;
            }
            k = i;
            while (k <= (n - 1)) {
                if (!k.equals(i) && !f.get(k).equals(0d)) {
                    list.add(f.get(i) / f.get(k));
                }
                k++;
            }
            i++;
        }
        return list;
    }


    private Double calculateAverageRatioValues(List<Double> f, List<Double> list) {

        if (f.size() == 0) {
            return null;
        }
        if (list == null)
            list = linesRatioValues(f);
        Double sum = 0d;
        Integer i = 0;
        while (i < list.size()) {
            sum += list.get(i);
            i++;
        }
        return sum / list.size();
    }

    private Double calculateMedianRatioValues(List<Double> f) {

        if (f.size() == 0) {
            return null;
        }
        List<Double> list = linesRatioValues(f);
        Collections.sort(list);
        if (list.size() % 2 != 0) {
            Integer index = (int) Math.ceil((list.size() / 2));
            return list.get(index);
        } else {
            Integer index1 = (list.size() / 2) - 1;
            Integer index2 = list.size() / 2;
            return (list.get(index1) + list.get(index2)) / 2d;
        }
    }

    private Double calculateStandardDeviationRatioValues(List<Double> f) {

        if (f.size() == 0) {
            return null;
        }
        List<Double> list = linesRatioValues(f);
        Integer n = list.size();
        Double m = calculateAverageRatioValues(f, list);
        Double sum = 0d;
        Integer i;
        for (i = 0; i < n; i++) {
            sum += (list.get(i) - m) * (list.get(i) - m);
        }
        return Math.sqrt((1d / n) * sum);
    }

    private Double calculateAverageAbsoluteDeviationRatioValues(List<Double> f) {

        if (f.size() == 0) {
            return null;
        }
        return 0.6475d * calculateStandardDeviationRatioValues(f);
    }

    public Double averageLinesRatioValuesByAperture(String category, String aperture) {

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

    public Double averageLinesRatioValues(String category) throws SQLException, ClassNotFoundException {

        try {
            return FluxDAO.findAverageFluxLinesRatioByCategory(category);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double medianLinesRatioValuesByAperture(String category, String aperture) {

        try {
            List<Double> f = FluxDAO.findAllFluxLinesRatioByCategoryAndAperture(category, aperture);
            return calculateMedianRatioValues(f);

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

    public Double medianLinesRatioValues(String category) throws SQLException, ClassNotFoundException {

        try {
            List<Double> f = FluxDAO.findAllFluxLinesRatioByCategory(category);
            return calculateMedianRatioValues(f);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double averageAbsoluteDeviationLinesRatioValuesByAperture(String category, String aperture) {

        try {
            List<Double> f = FluxDAO.findAllFluxLinesRatioByCategoryAndAperture(category, aperture);
            return calculateAverageAbsoluteDeviationRatioValues(f);

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

    public Double averageAbsoluteDeviationLinesRatioValues(String category) {

        try {
            List<Double> f = FluxDAO.findAllFluxLinesRatioByCategory(category);
            return calculateAverageAbsoluteDeviationRatioValues(f);

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

    public Double standarDeviationLinesRatioValuesByAperture(String category, String aperture) {

        try {
            List<Double> f = FluxDAO.findAllFluxLinesRatioByCategoryAndAperture(category, aperture);
            return calculateStandardDeviationRatioValues(f);

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

    public Double standarDeviationLinesRatioValues(String category) {

        try {
            List<Double> f = FluxDAO.findAllFluxLinesRatioByCategory(category);
            return calculateStandardDeviationRatioValues(f);

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