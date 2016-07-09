package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;

import java.util.ArrayList;
import java.util.List;

public class DemoSearchGalaxyLines {

    public static void main(String args[]) {

        Galaxy galaxy = new Galaxy("Mk622");
        List<String> lines = new ArrayList<String>();
        lines.add("NeVII14.3");
        lines.add("NeVII24.3");

        try {
            List<Flux> allLines = FluxDAO.findAllLinesByGalaxy(galaxy);
            List<Flux> resLines = FluxDAO.findLinesByGalaxy(galaxy, lines);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

    }
}
