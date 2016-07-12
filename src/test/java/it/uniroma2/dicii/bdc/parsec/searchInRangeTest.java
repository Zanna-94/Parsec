package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.Ascension;
import it.uniroma2.dicii.bdc.parsec.model.Declination;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import java.lang.Math;

/**
 * Requisite 6 Test.
 * <p/>
 * THE TEST FAIL BECAUSE java.lang.Math and trigonometric functions of jdbc
 * HAVE DIFFERENT APPROXIMATIONS.
 */
public class searchInRangeTest {

    @Test
    public void searchInRange() {

        Ascension a0 = new Ascension(0, 0, (float) 0);
        Declination d0 = new Declination('+', 0, 0, 0f);

        StatisticsQueryController controller = new StatisticsQueryController();
        List<Galaxy> results = controller.searchInRange(a0, d0, 10);


        Integer declinationDeg0 = d0.getDeclinationDeg();
        if (d0.getDeclinationSign() == '-')
            declinationDeg0 *= -1;

        float dec0 = 15 * (declinationDeg0 + d0.getDeclinationMin() / 60 + d0.getDeclinationSec() / 3600);
        float ra0 = 15 * (a0.getAscensionHour() + a0.getAscensionMin() / 60 + a0.getAscensionSec() / 3600);


        ArrayList<Galaxy> galaxies = new ArrayList<Galaxy>();
        for (Galaxy r : results) {
            galaxies.add(GalaxyDAO.findByName(r.getName()));
        }

        Double oldDistance = 0d;
        for (Galaxy galaxy : galaxies) {

            Ascension a1 = galaxy.getPosition().getAscension();
            Declination d1 = galaxy.getPosition().getDeclination();


            Integer declinationDeg1 = d1.getDeclinationDeg();
            if (d1.getDeclinationSign() == '-')
                declinationDeg1 *= -1;

            float dec1 = 15 * (declinationDeg1 + d1.getDeclinationMin() / 60 + d1.getDeclinationSec() / 3600);
            float ra1 = 15 * (a1.getAscensionHour() + a1.getAscensionMin() / 60 + a1.getAscensionSec() / 3600);

            double distance = Math.acos(Math.sin(ra0) * Math.sin(ra1) + Math.cos(ra0) * Math.cos(ra1) * Math.cos(dec0 - dec1));

            System.out.print(galaxy.getName() + " " + distance + "\n");
            assert (galaxy == galaxies.get(0) || oldDistance < distance);
            oldDistance = distance;

        }


    }
}
