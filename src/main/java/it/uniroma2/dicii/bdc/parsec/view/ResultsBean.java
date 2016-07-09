package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.Luminosity;
import it.uniroma2.dicii.bdc.parsec.model.Metallicity;
import it.uniroma2.dicii.bdc.parsec.model.dao.CSVManager;
import it.uniroma2.dicii.bdc.parsec.model.dao.LuminosityDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.MetallicityDAO;

import java.io.IOException;
import java.util.List;

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

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void fillResultsForGalaxyDescription() {

        List<Luminosity> resLum = LuminosityDAO.findByGalaxy(galaxy);
        List<Metallicity> resMet = MetallicityDAO.findByGalaxy(galaxy);

        String s = "<tr>\n<td>";
        s = s.concat(galaxy.getName());
        s = s.concat("</td>\n");

        String pos[] = { galaxy.getPosition().getAscension().getAscensionHour().toString(),
                galaxy.getPosition().getAscension().getAscensionMin().toString(),
                galaxy.getPosition().getAscension().getAscensionSec().toString(),
                galaxy.getPosition().getDeclination().getDeclinationSign().toString(),
                galaxy.getPosition().getDeclination().getDeclinationDeg().toString(),
                galaxy.getPosition().getDeclination().getDeclinationMin().toString(),
                galaxy.getPosition().getDeclination().getDeclinationSec().toString() };

        Integer i = 0;
        while (i < pos.length) {
            if ( pos[i].compareTo("-1") == 0 || pos[i].charAt(0) == '*') {
                pos[i] = "-";
            }
            i++;
        }
        String position = "<td>" +
                "A[" + pos[0] + "," + pos[1] + "," + pos[2] + "]-" +
                "D[" + pos[3] +"," + pos[4] + "," + pos[5] + "," + pos[6] + "]</td>";
        s = s.concat(position);

        String distance = "<td>" + galaxy.getPosition().getDistanceValue() + " ["
                + galaxy.getPosition().getRedshift() + "]</td>";
        s = s.concat(distance);

        Integer iter = 0;
        while (iter < Math.max(resLum.size(), resMet.size())) {
            results += s;

            if ( iter < resMet.size() ) {
                Float valueMet, errorMet;
                results = results.concat("<td>");
                if ((valueMet = resMet.get(iter).getValue()) != -1) {
                    results = results.concat(valueMet.toString());
                } else {
                    results = results.concat("- ");
                }
                results = results.concat("[");
                if ((errorMet = resMet.get(iter).getError()) != -1) {
                    results = results.concat(errorMet.toString());
                    results = results.concat("]</td>\n");
                } else {
                    results = results.concat("-]</td>\n");
                }
            }
            if ( iter < resLum.size() ) {
                Float valueLum;
                if ((valueLum = resLum.get(iter).getValue()) != -1) {
                    results = results.concat("<td>");
                    results = results.concat(valueLum.toString());
                    results = results.concat("</td>\n");
                } else {
                    results = results.concat("-</td>\n");
                }
            }
            results = results.concat("</tr>\n");
            iter++;
        }
    }
}