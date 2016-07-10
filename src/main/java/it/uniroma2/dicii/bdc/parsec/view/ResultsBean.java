package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.Luminosity;
import it.uniroma2.dicii.bdc.parsec.model.Metallicity;
import it.uniroma2.dicii.bdc.parsec.model.dao.CSV_DAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;

import java.util.*;

/**
 * 
 */
public class ResultsBean {

    private Galaxy galaxy;
    private String results;
    private List<Metallicity> metallicities;
    private List<Luminosity> luminosities;
    private List<Flux> fluxes;
    private Double value;
    private String category;
    private String operation;
    private String resolution;

    /**
     * Default constructor
     */
    public ResultsBean() {
    }

    public ResultsBean(Galaxy galaxy, List<Luminosity> luminosities, List<Metallicity> metallicities) {
        this.galaxy = galaxy;
        this.metallicities = metallicities;
        this.luminosities = luminosities;
    }

    public ResultsBean(List<Flux> fluxes) {
        this.fluxes = fluxes;
    }

    public ResultsBean(Double value, String operation, String category, String resolution) {
        this.value = value;
        this.category = category;
        this.operation = operation;
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Double getValue() {
        return value;
    }
    
    public void setValue(Double value) {
        this.value = value;
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

    public List<Flux> getFluxes() {
        return fluxes;
    }

    public void setFluxes(List<Flux> fluxes) {
        this.fluxes = fluxes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<Luminosity> getLuminosities() {
        return luminosities;
    }

    public void setLuminosities(List<Luminosity> luminosities) {
        this.luminosities = luminosities;
    }

    public List<Metallicity> getMetallicities() {
        return metallicities;
    }

    public void setMetallicities(List<Metallicity> metallicities) {
        this.metallicities = metallicities;
    }

    public void fillResultsForGalaxyDescription() {

        String s = "<tr>\n<td>";
        s = s.concat(galaxy.getName());
        s = s.concat("</td>\n");

        String pos[] = {galaxy.getPosition().getAscension().getAscensionHour().toString(),
                galaxy.getPosition().getAscension().getAscensionMin().toString(),
                galaxy.getPosition().getAscension().getAscensionSec().toString(),
                galaxy.getPosition().getDeclination().getDeclinationSign().toString(),
                galaxy.getPosition().getDeclination().getDeclinationDeg().toString(),
                galaxy.getPosition().getDeclination().getDeclinationMin().toString(),
                galaxy.getPosition().getDeclination().getDeclinationSec().toString()};

        Integer i = 0;
        while (i < pos.length) {
            if (pos[i].compareTo("-1") == 0 || pos[i].charAt(0) == '*') {
                pos[i] = "-";
            }
            i++;
        }
        String position = "<td>" +
                "A[" + pos[0] + "," + pos[1] + "," + pos[2] + "]-" +
                "D[" + pos[3] + "," + pos[4] + "," + pos[5] + "," + pos[6] + "]</td>";
        s = s.concat(position);

        String distance = "<td>" + galaxy.getPosition().getDistanceValue() + " ["
                + galaxy.getPosition().getRedshift() + "]</td>";
        s = s.concat(distance);

        Integer iter = 0;
        while (iter < Math.max(luminosities.size(), metallicities.size())) {
            results += s;

            if (iter < metallicities.size()) {
                Double valueMet;
                Float errorMet;
                results = results.concat("<td>");
                if ((valueMet = metallicities.get(iter).getVal()) != -1d) {
                    results = results.concat(valueMet.toString());
                } else {
                    results = results.concat("- ");
                }
                results = results.concat("[");
                if ((errorMet = metallicities.get(iter).getError()) != -1) {
                    results = results.concat(errorMet.toString());
                    results = results.concat("]</td>\n");
                } else {
                    results = results.concat("-]</td>\n");
                }
            }
            if (iter < luminosities.size()) {
                Double valueLum;
                if ((valueLum = luminosities.get(iter).getVal()) != -1d) {
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

    public void fillResultsForGalaxySpectralLines() {

        Integer iter = 0;
        while ( iter < fluxes.size()) {
            results += "<tr>\n<td>";
            results = results.concat(fluxes.get(0).getGalaxy().getName());
            results = results.concat("</td>\n<td>");
            results = results.concat(fluxes.get(iter).getAtom());
            results = results.concat("</td>\n");

            Double valueFlux;
            Float errorFlux;
            results = results.concat("<td>");

            if ((valueFlux = fluxes.get(iter).getVal()) != -1) {
                results = results.concat(valueFlux.toString());
            } else {
                results = results.concat("- ");
            }
            results = results.concat("[");
            if ((errorFlux = fluxes.get(iter).getError()) != -1) {
                results = results.concat(errorFlux.toString());
                results = results.concat("]</td>\n");
            } else {
                results = results.concat("-]</td>\n");
            }

            results = results.concat("<td>");
            if (fluxes.get(iter).getUpperLimit() != '*' && fluxes.get(iter).getUpperLimit() != '-') {
                results = results.concat("y</td>\n");
            } else {
                results = results.concat("n</td>\n");
            }

            results = results.concat("</tr>\n");
            iter++;
        }
    }

    public void fillLineFluxSelection() {

        fluxes = FluxDAO.findAllLinesByGalaxy(galaxy);
        results = "";
        Integer iter = 0;
        while (iter < fluxes.size()) {
            results = results.concat("<option value=");
            results = results.concat(fluxes.get(iter).getAtom());
            results = results.concat(" </option>");
            iter++;
        }
        /*while ( iter < fluxes.size() ) {
            results = results.concat("<li><a href=\"#\">Flux-");
            results = results.concat(fluxes.get(iter).getAtom());
            results = results.concat("</a></li>");
            iter++;
        }*/
    }

    public void fillResultsForFluxRatio() {

        results = "<td>";
        results = results.concat(fluxes.get(0).getGalaxy().getName());
        results = results.concat("</td><td>");
        Integer i = 0;
        while (fluxes.get(i).getAtom().compareTo(fluxes.get(i+1).getAtom()) == 0
                && i < ( fluxes.size() - 1) ) {
            i++;
        }
        Double ratio = fluxes.get(0).getVal() / fluxes.get(i+1).getVal();
        results = results.concat(ratio.toString());
        results = results.concat("</td><td>");
        if ( fluxes.get(0).getUpperLimit() != '*' && fluxes.get(0).getUpperLimit() != '-') {
            results = results.concat("<td>Upper limit</td>");
        } else if (fluxes.get(i+1).getUpperLimit() != '*' && fluxes.get(i+1).getUpperLimit() != '-') {
            results = results.concat("<td>Lower limit</td>");
        } else {
            results = results.concat("<td>-</td>");
        }
    }

    public void fillResultsForStatistics() {

        results = "<td>";
        results = results.concat(category);
        results = results.concat("</td><td>");
        results = results.concat(operation);
        results = results.concat("</td><td>");
        results = results.concat(value.toString());
        results = results.concat("</td><td>");
        if ( resolution.length() > 0) {
            results = results.concat("resolution");
        }
        results = results.concat("</td>");
    }

    public void getListOfFiles() {

        CSVManager cm = new CSVManager();
        List<String> files = cm.getAllFiles();

        if (files.size() == 0) {
            results = "No files uploaded.";
        } else {
            results += "<ul class=\"list-group\">";
                    Integer i;
            for ( i = 0; i < files.size(); i++) {
                results = results.concat("<li class=\"list-group-item\">");
                results = results.concat(files.get(i));
                results = results.concat("</li>");
            }
            results += "</ul>";
        }
    }
}
