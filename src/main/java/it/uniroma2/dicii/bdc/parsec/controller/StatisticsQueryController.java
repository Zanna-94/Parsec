package it.uniroma2.dicii.bdc.parsec.controller;


import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticsQueryController {


    public StatisticsQueryController() {
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

    private List<Double> linesRatioValues(List<Double> f) {

        List<Double> list = new ArrayList<Double>();
        Integer n = f.size();
        Integer i = 0, j, k;
        while ( i < n) {
            j = i;
            while (j >= 0) {
                if (!i.equals(j) && !f.get(j).equals(0d))
                    list.add(f.get(i) / f.get(j));
                j--;
            }
            k = i;
            while (k <= (n-1)) {
                if (!k.equals(i) && !f.get(k).equals(0d)){
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