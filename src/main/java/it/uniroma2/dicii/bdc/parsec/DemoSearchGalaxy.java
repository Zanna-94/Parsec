package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;

/**
 * Created by laura_trive on 09/07/16.
 */
public class DemoSearchGalaxy {

    public static void main(String args[]) {

        Galaxy galaxy = new Galaxy("Mk622");

        try {
            GalaxyDAO.findByName(galaxy.getName());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

    }
}
