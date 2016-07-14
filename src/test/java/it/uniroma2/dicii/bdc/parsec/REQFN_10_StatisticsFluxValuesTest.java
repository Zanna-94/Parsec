package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class REQFN_10_StatisticsFluxValuesTest {

    /**
     * REQ-FN-10: Values of ratio between lines by spectral group.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void getAverageByCategory() throws SQLException, ClassNotFoundException {

        QueryBoundary q = new QueryBoundary();
        q.setCategory("S2");

        Double expectedAvg = 16.9d;

        q.setOperation("avg");
        assertEquals(expectedAvg, q.getStatistics().getValue(), 0.1d);
    }

    @Test
    public void getMedianByCategory() throws SQLException, ClassNotFoundException {
        QueryBoundary s = new QueryBoundary();
        s.setCategory("LIN");
        s.setOperation("med");
        Double expectedMed = 1d;

        assertEquals(expectedMed, s.getStatistics().getValue(), 0.1d);
    }

    @Test
    public void getStardardDeviationByCategory() throws SQLException, ClassNotFoundException {
        QueryBoundary s = new QueryBoundary();
        s.setCategory("LIN");
        s.setOperation("std");
        Double expectedMed = 79d;

        assertEquals(expectedMed, s.getStatistics().getValue(), 0.2d);
    }

    @Test
    public void getAverageStandarDeviationByCategory() throws SQLException, ClassNotFoundException {
        QueryBoundary s = new QueryBoundary();
        s.setCategory("LIN");
        s.setOperation("astd");
        Double expectedMed = 79d;

        assertEquals(expectedMed, s.getStatistics().getValue(), 0.1d);
    }

    /**
     * REQ-FN-10.1: Values of ratio between lines by spectral group,
     * specifying a type of aperture.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void getAverageByCategoryAndAperture() throws SQLException, ClassNotFoundException {

        QueryBoundary q = new QueryBoundary();
        q.setCategory("S2");
        q.setResolution("3x3");

        Double expectedAvg = 13d;

        q.setOperation("avg");
        assertEquals(expectedAvg, q.getStatistics().getValue(), 0.1d);
    }

    @Test
    public void getMedianByCategoryAndAperture() throws SQLException, ClassNotFoundException {
        QueryBoundary s = new QueryBoundary();
        s.setResolution("3x3");
        s.setCategory("S2");
        s.setOperation("med");
        Double expectedMed = 1d;

        assertEquals(expectedMed, s.getStatistics().getValue(), 0.1d);
    }

    @Test
    public void getStardardDeviationByCategoryAndaperture() throws SQLException, ClassNotFoundException {
        QueryBoundary s = new QueryBoundary();
        s.setResolution("3x3");
        s.setCategory("LIN");
        s.setOperation("std");
        Double expectedMed = 43d;

        assertEquals(expectedMed, s.getStatistics().getValue(), 0.6d);
    }

    @Test
    public void getAverageStandardDeviationByCategoryAndAperture() throws SQLException, ClassNotFoundException {
        QueryBoundary s = new QueryBoundary();
        s.setResolution("3x3");
        s.setCategory("LIN");
        s.setOperation("astd");
        Double expectedMed = 79d;

        assertEquals(expectedMed, s.getStatistics().getValue(), 0.1d);
    }
}
