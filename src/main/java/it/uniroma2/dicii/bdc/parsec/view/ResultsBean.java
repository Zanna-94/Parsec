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

        // // TODO: 09/07/16 check for null values and if true -

        String position = "<td>" +
                "A[" + galaxy.getPosition().getAscension().getAscensionHour() + ","
                + galaxy.getPosition().getAscension().getAscensionMin() + ","
                + galaxy.getPosition().getAscension().getAscensionSec() + "]-" +
                "D[" + galaxy.getPosition().getDeclination().getDeclinationSign() +","
                + galaxy.getPosition().getDeclination().getDeclinationDeg() + ","
                + galaxy.getPosition().getDeclination().getDeclinationMin() + ","
                + galaxy.getPosition().getDeclination().getDeclinationSec() + "]<\td>";
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
            Float valueLum, errorLum;
            if ((valueLum = lum.getValue()) != -1) {
                results = results.concat(valueLum.toString());
                results = results.concat("</td>\n");
            } else {
                results = results.concat("- ");
            }
            if ((errorLum = lum.getError()) != -1) {
                results = results.concat("[");
                results = results.concat(errorLum.toString());
                results = results.concat("]</td>\n");
            } else {
                results = results.concat("-]</td>\n");
            }
        }
    }

}