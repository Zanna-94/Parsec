package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.SearchQueryController;
import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class REQFN_5_SearchGalaxyByNameTest {

    /**
     * REQ-FN-5: Search of a galaxy object by name.
     * [position, distance, redshift, luminosity, metallicity and relative errors
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void searchGalaxyDescriptionByName()
            throws SQLException, ClassNotFoundException, IOException {

        Position position = new Position(-1f,
                0.023229f,
                new Declination('+', 0, 39, 15.2424f),
                new Ascension(8, 7, 41.003f));

        Galaxy g = new Galaxy();
        g.setName("Mrk622");
        g.setPosition(position);

        Luminosity l = new Luminosity();
        l.setVal(40.63d);

        Metallicity m = new Metallicity();
        m.setVal(1.0);
        m.setError(-1f);

        QueryBoundary query = new QueryBoundary();
        query.setGalaxyName(g.getName());
        SearchQueryController q = new SearchQueryController();

        List<String> foundGalaxy = q.searchGalaxyDescriptionByName(query).get(0);

        /*  test pass if every comparison between info of
            Galaxy and relative lumiosity and metallicity searched
            is equal to info of Galaxy and luminosity and metallicity known    */

        assertEquals(g.getName(), foundGalaxy.get(0));

        assertEquals(g.getPosition().getAscension().getAscensionHour(),
                new Integer (foundGalaxy.get(1)));

        assertEquals(g.getPosition().getAscension().getAscensionMin(),
                new Integer (foundGalaxy.get(2)));

        assertEquals(g.getPosition().getAscension().getAscensionSec(),
                new Float(foundGalaxy.get(3)));

        assertEquals(g.getPosition().getDeclination().getDeclinationSign(),
                new Character(foundGalaxy.get(4).charAt(0)));

        assertEquals(g.getPosition().getDeclination().getDeclinationDeg(),
                new Integer (foundGalaxy.get(5)));

        assertEquals(g.getPosition().getDeclination().getDeclinationMin(),
                new Integer (foundGalaxy.get(6)));

        assertEquals(g.getPosition().getDeclination().getDeclinationSec(),
                new Float (foundGalaxy.get(7)));

        assertEquals(g.getPosition().getDistanceValue(),
                new Float (foundGalaxy.get(8)));

        assertEquals(g.getPosition().getRedshift(),
                new Float (foundGalaxy.get(9)));

        assertEquals(l.getVal(), Double.parseDouble(foundGalaxy.get(10)), 0.0001);

        assertEquals(m.getVal(), Double.parseDouble(foundGalaxy.get(11)), 0.0001);

        assertEquals(m.getError(), new Float (foundGalaxy.get(12)));
    }
}