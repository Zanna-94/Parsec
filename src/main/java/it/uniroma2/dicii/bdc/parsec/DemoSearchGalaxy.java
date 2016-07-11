package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.Ascension;
import it.uniroma2.dicii.bdc.parsec.model.Declination;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;

import java.util.HashMap;
import java.util.List;

public class DemoSearchGalaxy {

    private static void byName(String name) {
        Galaxy galaxy = new Galaxy(name);
/*
        try {
            GalaxyDAO.findByName(galaxy.getName());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }*/
    }

    private static void byRedshift() {

        List<Galaxy> list = GalaxyDAO.findLower((float) 0.0612);
        for (Galaxy g : list)
            System.out.print(g.getName() + "\n");
    }

    private static void ratio() {

        StatisticsQueryController controller = new StatisticsQueryController();
        HashMap<String, Double> h = controller.calculateRatio("OI63", "IRAS00182-7112");

        System.out.print(h.values());
    }

    private static void inRange() {
        StatisticsQueryController controller = new StatisticsQueryController();
        Ascension ascension = new Ascension(0, 0, (float) 0);
        Declination declination = new Declination(null, 0, 0, 0f);
        List<Galaxy> galaxies = controller.searchInRange(ascension, declination, 10);

        for (Galaxy galaxy : galaxies)
            System.out.print(galaxy.getName());

    }

    public static void main(String args[]) {

        inRange();

        System.exit(0);
    }
}
