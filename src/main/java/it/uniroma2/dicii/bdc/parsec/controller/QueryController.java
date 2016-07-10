package it.uniroma2.dicii.bdc.parsec.controller;

import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.LuminosityDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.MetallicityDAO;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;

import javax.persistence.NoResultException;
import java.util.*;

/**
 *
 */
public class QueryController {

    /**
     * Default constructor
     */
    public QueryController() {
    }

    /**
     * @param redshift    value on which search
     * @param searchLower It indicates whether we have to search for all values less than specified one
     * @return list of {@link Galaxy} ordered by redshift value
     */
    public List<Galaxy> searchForRedshift(float redshift, boolean searchLower) {

        if (searchLower)
            return GalaxyDAO.findLower(redshift);
        else
            return GalaxyDAO.findGreater(redshift);
    }

    public List<Galaxy> searchInRange(Position position) {
        ArrayList<Galaxy> galaxies = null;
        // TODO: 10/07/16

        return galaxies;
    }

    public Float calculateRatio(String fluxLine) {
        // TODO: 10/07/16
        return null;
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

    public List<Metallicity> searchMetallicityByGalaxy(String galaxyName) {

        try {
            return MetallicityDAO.findByGalaxy(new Galaxy(galaxyName));

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Luminosity> searchLuminosityByGalaxy(String galaxyName) {

        try {
            return LuminosityDAO.findByGalaxy(new Galaxy(galaxyName));

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> composeLinesList(QueryBoundary query) {

        List<java.lang.String> l = new ArrayList<java.lang.String>();

        if ( query.isAtomOIV259() != null && query.isAtomOIV259() ) {
            l.add("OIV25.9");
        }
        if ( query.isAtomNEV143() != null && query.isAtomNEV143() ) {
            l.add("NEV14.3");
        }
        if ( query.isAtomNEV243() != null && query.isAtomNEV243() ) {
            l.add("NEV24.3");
        }
        if ( query.isAtomOIII52() != null && query.isAtomOIII52() ) {
            l.add("OIII52");
        }
        if ( query.isAtomNIII57() != null && query.isAtomNIII57() ) {
            l.add("NIII57");
        }
        if ( query.isAtomOI63() != null && query.isAtomOI63() ) {
            l.add("OI63");
        }
        if ( query.isAtomOIII88() != null && query.isAtomOIII88() ) {
            l.add("OIII88");
        }
        if ( query.isAtomNII122() != null && query.isAtomNII122() ) {
            l.add("NII122");
        }
        if ( query.isAtomOI145() != null && query.isAtomOI145() ) {
            l.add("OI145");
        }
        if ( query.isAtomCII158() != null && query.isAtomCII158() ) {
            l.add("CII158");
        }
        if ( query.isAtomSIV105() != null && query.isAtomSIV105() ) {
            l.add("SIV10.5");
        }
        if ( query.isAtomNEII128() != null && query.isAtomNEII128() ) {
            l.add("NEII12.8");
        }
        if ( query.isAtomNEIII156() != null && query.isAtomNEIII156() ) {
            l.add("NEIII15.6");
        }
        if ( query.isAtomSIII187() != null && query.isAtomSIII187() ) {
            l.add("SIII18.7");
        }
        if ( query.isAtomSIII335() != null && query.isAtomSIII335() ) {
            l.add("SIII33.5");
        }
        if ( query.isAtomSII348() != null && query.isAtomSII348() ) {
            l.add("SII34.8");
        }
        return l;
    }

    public List<Flux> searchAllGalaxySpectralLines(QueryBoundary query) {

        Galaxy galaxy = new Galaxy(query.getGalaxyName());
        try {
            return FluxDAO.findAllLinesByGalaxy(galaxy);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Flux> searchGalaxySpectralLinesForRatio(QueryBoundary query) {

        Galaxy galaxy = new Galaxy(query.getGalaxyName());
        List<String> lines = new ArrayList<String>();
        lines.add(query.getFluxNum());
        lines.add(query.getFluxDen());
        try {
            return FluxDAO.findLinesByGalaxy(galaxy, lines);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double calculateStatistics(QueryBoundary query) {

        if (query.getResolution() == null) {
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

    public List<Flux> searchGalaxySpectralLines(QueryBoundary query) {

        Galaxy galaxy = new Galaxy(query.getGalaxyName());
        List<String> lines = composeLinesList(query);
        try {
            return FluxDAO.findLinesByGalaxy(galaxy, lines);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Double> linesRatioValues(List<Double> f) {

        List<Double> list = new ArrayList<Double>();
        Integer n = f.size();
        Integer i = 0, j, k;
        while ( i < n) {
            j = i;
            while (j >= 0) {
                if (!i.equals(j))
                    list.add(f.get(i) / f.get(j));
                j--;
            }
            k = i;
            while (k <= (n-1)) {
                if (!k.equals(i)){
                    list.add(f.get(i) / f.get(k));
                }
                k++;
            }
            i++;
        }
        return list;
    }

    private Double calculateAverageRatioValues(List<Double> f, List<Double> list) {

        if ( f.size() == 0) {
            return null;
        }
        if (list == null)
            list = linesRatioValues(f);
        Double sum = 0d;
        Integer i = 0;
        while ( i < list.size()) {
            sum += list.get(i);
            i++;
        }
        return sum/list.size();
    }

    private Double calculateMedianRatioValues(List<Double> f) {

        if (f.size() == 0) {
            return null;
        }
        List<Double> list = linesRatioValues(f);
        Collections.sort(list);
        if (list.size() % 2 != 0) {
            Integer index = (int) Math.ceil((list.size()/2));
            return list.get(index);
        } else {
            Integer index1 = (list.size()/2) - 1;
            Integer index2 = list.size()/2;
            return  (list.get(index1) + list.get(index2)) / 2d;
        }
    }

    private Double calculateStandardDeviationRatioValues(List<Double> f) {

        if ( f.size() == 0) {
            return null;
        }
        List<Double> list = linesRatioValues(f);
        Integer n = list.size();
        Double m = calculateAverageRatioValues(f, list);
        Double sum = 0d;
        Integer i;
        for ( i = 0; i < n; i++ ) {
            sum += (list.get(i) - m)*(list.get(i) - m);
        }
        return Math.sqrt((1d/n)*sum);
    }

    private Double calculateAverageAbsoluteDeviationRatioValues(List<Double> f) {

        if ( f.size() == 0) {
            return null;
        }
        return 0.6475d * calculateStandardDeviationRatioValues(f);
    }

    public Double averageLinesRatioValuesByAperture(String category, String aperture) {

        try {
            List<Double> f = FluxDAO.findLinesByCategoryAndAperture(category, aperture);
            return calculateAverageRatioValues(f, null);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double averageLinesRatioValues(String category) {

        try {
            List<Double> f = FluxDAO.findLinesByCategory(category);
            return calculateAverageRatioValues(f, null);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double medianLinesRatioValuesByAperture(String category, String aperture) {

        try {
            List<Double> f = FluxDAO.findLinesByCategoryAndAperture(category, aperture);
            return calculateMedianRatioValues(f);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double medianLinesRatioValues(String category) {

        try {
            List<Double> f = FluxDAO.findLinesByCategory(category);
            return calculateMedianRatioValues(f);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double averageAbsoluteDeviationLinesRatioValuesByAperture(String category, String aperture) {

        try {
            List<Double> f = FluxDAO.findLinesByCategoryAndAperture(category, aperture);
            return calculateAverageAbsoluteDeviationRatioValues(f);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double averageAbsoluteDeviationLinesRatioValues(String category) {

        try {
            List<Double> f = FluxDAO.findLinesByCategory(category);
            return calculateAverageAbsoluteDeviationRatioValues(f);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double standarDeviationLinesRatioValuesByAperture(String category, String aperture) {

        try {
            List<Double> f = FluxDAO.findLinesByCategoryAndAperture(category, aperture);
            return calculateStandardDeviationRatioValues(f);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double standarDeviationLinesRatioValues(String category) {

        try {
            List<Double> f = FluxDAO.findLinesByCategory(category);
            return calculateStandardDeviationRatioValues(f);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}