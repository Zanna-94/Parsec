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
                "HS0052+2536",
                "IRAS05024-1941",
                "NGC1961",
                "NGC5506",
                "NGC1705",
                "NGC7591",
                "NGC5253",
                "UGC4483"
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
