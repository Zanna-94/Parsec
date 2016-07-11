package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.SearchQueryController;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class RatioTwoLinesValuesTest {

    @Test
    public void getTwoFluxLinesValuesRatio() throws SQLException, ClassNotFoundException {

        SearchQueryController c = new SearchQueryController();

        QueryBoundary q = new QueryBoundary();
        q.setGalaxyName("Mrk622");
        q.setFluxNum("NeV14.3");
        q.setFluxDen("NeV24.3");

        Double ratioExpected = 1.46/2.19d;

        System.out.printf("%s",FluxDAO.findRatioBetweenTwoLinesFluxValues(
                q.getGalaxyName(), q.getFluxNum(), q.getFluxDen()));

        System.out.printf("..... %f, %s",ratioExpected, c.calculateRatio(q).toString());
        assertEquals(ratioExpected,
                Double.parseDouble(c.calculateRatio(q).get(0)),
                    0.001d);
    }
}
