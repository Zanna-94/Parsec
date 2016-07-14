package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.SearchQueryController;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class REQFN_9_RatioTwoLinesValuesTest {

    /**
     * REQ-FN-9: Values of ratio between lines by spectral group.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void getTwoFluxLinesValuesRatio() throws SQLException, ClassNotFoundException {

        SearchQueryController c = new SearchQueryController();

        QueryBoundary q = new QueryBoundary();
        q.setGalaxyName("Mrk622");
        q.setFluxNum("NeII12.8");
        q.setFluxDen("NeV14.3");

        /* ratio expected based on two values relative to NeII12.8 and NeV14.3   */
        Double ratioExpected = 9.73/3.81;

        assertEquals(ratioExpected,
                Double.parseDouble(c.calculateRatio(q).get(0)),
                    0.001d);
    }
}
