package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class StatisticsFluxValuesTest {

    @Test
    public void getStatisticsByCategory() throws SQLException, ClassNotFoundException {

        QueryBoundary q = new QueryBoundary();
        q.setCategory("S2");

        Double expectedAvg = 1.92d;
        Double expectedMedian = 0.097786d;
        Double expectedStdDev = 149.597636d;
        Double expectedAbsStdDev = 96.864469d;

        q.setOperation("avg");
        //assertEquals(expectedAvg, q.getStatistics().getValue(), 0.001d);
        q.setOperation("med");
        //assertEquals(expectedMedian, q.getStatistics().getValue(), 0.001d);
        q.setOperation("std");
        //assertEquals(expectedStdDev, q.getStatistics().getValue(), 5d);
        q.setOperation("astd");
        assertEquals(expectedAbsStdDev, q.getStatistics().getValue(), 1d);

    }

    @Test
    public void getStatisticsByCategoryAndAperture() {

        StatisticsQueryController q = new StatisticsQueryController();

        String category = "S2";
        Double expectedAvg = 1.92d;
        Double expectedMedian = 0.097786d;
        Double expectedStdDev = 149.597636d;
        Double expectedAbsStdDev = 96.864469d;
        String resolution = "c";

        System.out.printf("avg aperture %f\n", q.averageLinesRatioValuesByAperture(category, resolution));
        System.out.printf("med aperture %f\n", q.medianLinesRatioValuesByAperture(category, resolution));
        System.out.printf("std aperture %f\n", q.standarDeviationLinesRatioValuesByAperture(category, resolution));
        System.out.printf("astd aperture %f\n", q.averageAbsoluteDeviationLinesRatioValuesByAperture(category, resolution));


    }

}
