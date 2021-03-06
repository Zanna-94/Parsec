package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * REQ-FN-7: Search first n-galaxies ordered by redshift value greater
 * or lower than a given one
 */
public class REQFN_7_SearchForRedshift {

    @Test
    public void searchForRedshift() {

        Float redshift = 0.6f;

        StatisticsQueryController controller = new StatisticsQueryController();
        // 2th argument is true to search for galaxies with redshift lower than it
        List<Galaxy> galaxies = controller.searchForRedshift(redshift, true);

        for (Galaxy galaxy : galaxies)
            Assert.assertTrue(galaxy.getPosition().getRedshift() <= redshift);

        redshift = 0.0612f;

        galaxies = controller.searchForRedshift(redshift, true);

        for (Galaxy galaxy : galaxies)
            Assert.assertTrue(galaxy.getPosition().getRedshift() <= redshift);


        redshift = 1.1f;

        galaxies = controller.searchForRedshift(redshift, false);

        for (Galaxy galaxy : galaxies)
            Assert.assertTrue(galaxy.getPosition().getRedshift() >= redshift);

    }

}
