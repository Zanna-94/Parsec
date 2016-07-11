package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.SearchQueryController;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchLinesByGalaxyTest {

    @Test
    public void getFluxLinesValuesByGalaxy() throws SQLException, IOException, ClassNotFoundException {

        SearchQueryController s = new SearchQueryController();
        QueryBoundary q = new QueryBoundary();
        q.setGalaxyName("Mrk622");

        /*  examples of line flux values of galaxy Mrk622   */
        Double val1 = 10.9d;
        Double val2 = -1.0d;
        Double val3 = 9.5d;

        List<List<String>> result = s.searchFluxLinesValuesByGalaxy(q);

        assertEquals(Double.parseDouble(result.get(1).get(2)), val1, 0.1d);
        assertEquals(Double.parseDouble(result.get(2).get(2)), val2, 0.1d);
        assertEquals(Double.parseDouble(result.get(3).get(2)), val3, 0.1d);
    }
}
