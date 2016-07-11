package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class StatisticsFluxValuesTest {

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
        assertEquals(expectedAvg, FluxDAO.findAverageFluxLinesRatioByCategory("S2"), 0.1d);
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

        StatisticsQueryController s = new StatisticsQueryController();
        QueryBoundary q = new QueryBoundary();
        q.setCategory("S2");

        Double expectedAvg = 10.3d;

        q.setOperation("avg");
        assertEquals(expectedAvg, FluxDAO.findAverageFluxLinesRatioByCategoryAndAperture("S2", "c"), 0.1d);
    }

}
