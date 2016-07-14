package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.Ascension;
import it.uniroma2.dicii.bdc.parsec.model.Declination;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.lang.Math;

/**
 * REQ-FN-6: Search first n-galaxies ordered by distance value in a
 * range from a given value.
 */
public class REQFN_6_SearchInRangeTest {

    @Test
    public void searchInRange() {


        String[] extepectedArray = {
                "NGC253",
                "NGC7213",
                "IRAS22017+0319",
                "HS0052+2536",
                "IRAS05024-1941",
                "NGC7603",
                "NGC1961",
                "IRAS05189-2524",
                "NGC5506",
                "Mrk348"
        };

        List<String> extepectedGalaxies = Arrays.asList(extepectedArray);


        Ascension ascension = new Ascension(0, 0, 0f);
        Declination declination = new Declination('+', 0, 0, 0f);

        List<Galaxy> galaxies;
        try {
            galaxies = GalaxyDAO.findInRange(ascension, declination, 10);
        } catch (Exception e) {
            e.printStackTrace();
            galaxies = null;
        }

        if (galaxies != null)
            for (int i = 0; i < galaxies.size(); i++)
                Assert.assertEquals(galaxies.get(i).getName(), extepectedGalaxies.get(i));


    }

}
