package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import org.junit.Test;

import java.util.List;

/**
 * REQ-FN-7: Search first n-galaxies ordered by redshift value greater
 * or lower than a given one
 */
public class searchForRedshift {

    @Test
    public void searchForRedshift() {

        Float redshift = 0.6f;

        StatisticsQueryController controller = new StatisticsQueryController();
        List<Galaxy> galaxies = controller.searchForRedshift(redshift, true);

        for (Galaxy galaxy : galaxies)
            assert galaxy.getPosition().getRedshift() <= redshift;

        redshift = 0.0612f;

        galaxies = controller.searchForRedshift(redshift, true);

        for (Galaxy galaxy : galaxies)
            assert galaxy.getPosition().getRedshift() <= redshift;


        redshift = 1.1f;

        galaxies = controller.searchForRedshift(redshift, false);

        for (Galaxy galaxy : galaxies)
            assert galaxy.getPosition().getRedshift() >= redshift;

    }

}
