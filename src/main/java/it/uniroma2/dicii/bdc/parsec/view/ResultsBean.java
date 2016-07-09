package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.Luminosity;
import it.uniroma2.dicii.bdc.parsec.model.Metallicity;
import it.uniroma2.dicii.bdc.parsec.model.dao.LuminosityDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.MetallicityDAO;

/**
 * 
 */
public class ResultsBean {

    private Galaxy galaxy;
    private String results;

    /**
     * Default constructor
     */
    public ResultsBean() {
    }

    public ResultsBean(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public void fillResultsForGalaxyDescription() {

        Luminosity lum;
        Metallicity met;

        results = "<td>";
        results = results.concat(galaxy.getName());
        results = "<\td>\n";

        String pos[] = { galaxy.getPosition().getAscension().getAscensionHour().toString(),
                galaxy.getPosition().getAscension().getAscensionMin().toString(),
                galaxy.getPosition().getAscension().getAscensionSec().toString(),
                galaxy.getPosition().getDeclination().getDeclinationSign().toString(),
                galaxy.getPosition().getDeclination().getDeclinationDeg().toString(),
                galaxy.getPosition().getDeclination().getDeclinationMin().toString(),
                galaxy.getPosition().getDeclination().getDeclinationSec().toString() };

        Integer i = 0;
        while (i < pos.length) {
            if (pos[i] == null || pos[i].compareTo("-1") == 0) {
                pos[i] = "-";
            }
        }
        String position = "<td>" +
                "A[" + pos[0] + "," + pos[1] + "," + pos[2] + "]-" +
                "D[" + pos[3] +"," + pos[4] + "," + pos[5] + "," + pos[6] + "]<\td>";
        results = results.concat(position);

        String distance = "<td>" + galaxy.getPosition().getDistanceValue() + " ["
                + galaxy.getPosition().getRedshift() + "]<\td>";
        results = results.concat(distance);

        if ((met = MetallicityDAO.findByGalaxy(galaxy.getName())) != null) {
            Float valueMet, errorMet;
            if ((valueMet = met.getValue()) != -1) {
                results = results.concat(valueMet.toString());
                results = results.concat("</td>\n");
            } else {
                results = results.concat("- ");
            }
            if ((errorMet = met.getError()) != -1) {
                results = results.concat("[");
                results = results.concat(errorMet.toString());
                results = results.concat("]</td>\n");
            } else {
                results = results.concat("-]</td>\n");
            }
        }
        if ((lum = LuminosityDAO.findByGalaxy(galaxy.getName())) != null) {
            Float valueLum;
            if ((valueLum = lum.getValue()) != -1) {
                results = results.concat(valueLum.toString());
                results = results.concat("</td>\n");
            } else {
                results = results.concat("-</td>\n");
            }
        }
    }

}