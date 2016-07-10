package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.SearchQueryController;
import it.uniroma2.dicii.bdc.parsec.model.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchGalaxyTest {

    @Test
    public void searchGalaxyDescriptionByName() throws SQLException, ClassNotFoundException {

        Galaxy g = new Galaxy();
        g.setName("Mrk622");
        g.setPosition(new Position(-1f, 0.023229f,
                new Declination('+', 0, 39, 15.2424f),
                new Ascension(8, 7, 41.003f)));
        Luminosity l = new Luminosity();
        l.setVal(40.63d);
        Metallicity m = new Metallicity();
        m.setVal(1.0);
        m.setError(-1f);

        SearchQueryController q = new SearchQueryController();
        List<List<String>> result = q.searchGalaxyDescriptionByName("Mrk622");

        assert g.getName().equals(result.get(0).get(0));
        assert g.getPosition().getAscension().getAscensionHour()
                .equals(Integer.parseInt(result.get(0).get(1)));
        assert g.getPosition().getAscension().getAscensionMin()
                .equals(Integer.parseInt(result.get(0).get(2)));
        assert g.getPosition().getAscension().getAscensionSec()
                .equals(Float.parseFloat(result.get(0).get(3)));
        assert g.getPosition().getDeclination().getDeclinationSign()
                .equals(result.get(0).get(4).charAt(0));
        assert g.getPosition().getDeclination().getDeclinationDeg()
                .equals(Integer.parseInt(result.get(0).get(5)));
        assert g.getPosition().getDeclination().getDeclinationMin()
                .equals(Integer.parseInt(result.get(0).get(6)));
        assert g.getPosition().getDeclination().getDeclinationSec()
                .equals(Float.parseFloat(result.get(0).get(7)));
        assert g.getPosition().getDistanceValue()
                .equals(Float.parseFloat(result.get(0).get(8)));
        assert g.getPosition().getRedshift()
                .equals(Float.parseFloat(result.get(0).get(9)));
        assertEquals(l.getVal(), Double.parseDouble(result.get(0).get(10)), 0.0001);
        assertEquals(m.getVal(), Double.parseDouble(result.get(0).get(11)), 0.0001);
        assert m.getError().equals(Float.parseFloat(result.get(0).get(12)));
    }
}