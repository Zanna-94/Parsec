package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.Ascension;
import it.uniroma2.dicii.bdc.parsec.model.Declination;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.Position;
import org.junit.Test;

public class SearchGalaxyTest {

    @Test
    public void searchGalaxyInfoByName() {

        Galaxy g = new Galaxy();
        g.setName("Mrk622");
        g.setAlterName("UGC4229");
        g.setCategory("S2");
        g.setPosition(new Position(-1f, 0.023229f,
                new Declination('+',0,39,15.2424f),
                new Ascension(8,7,41.003f)));

        StatisticsQueryController q = new StatisticsQueryController();

        Galaxy r = q.searchGalaxyByName("Mrk622");

        assert g.getName().equals(r.getName());
        assert g.getAlterName().equals(r.getAlterName());
        assert g.getCategory().equals(r.getCategory());
        assert g.getPosition().getDistanceValue().equals(r.getPosition().getDistanceValue());
        assert g.getPosition().getRedshift().equals(r.getPosition().getRedshift());
        assert g.getPosition().getAscension().getAscensionHour().equals(r.getPosition().getAscension().getAscensionHour());
        assert g.getPosition().getAscension().getAscensionMin().equals(r.getPosition().getAscension().getAscensionMin());
        assert g.getPosition().getAscension().getAscensionSec().equals(r.getPosition().getAscension().getAscensionSec());
        assert g.getPosition().getDeclination().getDeclinationSign().equals(r.getPosition().getDeclination().getDeclinationSign());
        assert g.getPosition().getDeclination().getDeclinationDeg().equals(r.getPosition().getDeclination().getDeclinationDeg());
        assert g.getPosition().getDeclination().getDeclinationMin().equals(r.getPosition().getDeclination().getDeclinationMin());
        assert g.getPosition().getDeclination().getDeclinationSec().equals(r.getPosition().getDeclination().getDeclinationSec());
    }

}