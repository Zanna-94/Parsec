package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.QueryController;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;

import java.util.HashMap;
import java.util.List;

public class DemoSearchGalaxy {

    private static void byName(String name) {
        Galaxy galaxy = new Galaxy(name);

        try {
            GalaxyDAO.findByName(galaxy.getName());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void byRedshift() {

        List<Galaxy> list = GalaxyDAO.findLower((float) 0.0612);
        for (Galaxy g : list)
            System.out.print(g.getName() + "\n");
    }

    private static void ratio() {

        QueryController controller = new QueryController();
        HashMap<String, Double> h = controller.calculateRatio("OI63", "IRAS00182-7112");

        System.out.print(h.values());
    }

    public static void main(String args[]) {

        ratio();

        System.exit(0);
    }
}
