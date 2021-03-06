package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;

import java.io.IOException;
import java.util.List;

public class DemoStatistics {

    public static void main(String args[]) throws IOException {

        StatisticsQueryController q = new StatisticsQueryController();

        String category = "S2";
        String resolution = "c";

        List<Double> fluxes = FluxDAO.findLinesByCategory(category);
        System.out.printf("values %s\n", fluxes);

        if (fluxes == null) {
            System.out.printf("No values\n");
        } else {
            try {
                System.out.printf("avg %f\n", q.averageLinesRatioValues(category));
                System.out.printf("med %f\n", q.medianLinesRatioValues(category));
                System.out.printf("std %f\n", q.standarDeviationLinesRatioValues(category));
                System.out.printf("astd %f\n", q.averageAbsoluteDeviationLinesRatioValues(category));
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        List<Double> fluxesByAperture = FluxDAO.findLinesByCategoryAndAperture(category, resolution);

        if (fluxesByAperture == null) {
            System.out.printf("No values\n");
        } else {
            System.out.printf("avg aperture %f\n", q.averageLinesRatioValuesByAperture(category, resolution));
            System.out.printf("med aperture %f\n", q.medianLinesRatioValuesByAperture(category, resolution));
            System.out.printf("std aperture %f\n", q.standarDeviationLinesRatioValuesByAperture(category, resolution));
            System.out.printf("astd aperture %f\n", q.averageAbsoluteDeviationLinesRatioValuesByAperture(category, resolution));
        }
        System.exit(0);
    }
}
