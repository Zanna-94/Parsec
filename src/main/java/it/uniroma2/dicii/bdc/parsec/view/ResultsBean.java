package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;

import java.util.*;

/**
 * Class to manage dynamic composition of jsp page after query to show returning results.
 */
public class ResultsBean {

    private String results;
    private List<List<String>> description;
    private List<String> ratio;
    private Double value;
    private String category;
    private String operation;
    private String resolution;


    /**
     * Default constructor
     */
    public ResultsBean() {
    }

    public ResultsBean(List<List<String>> description) {
        this.description = description;
    }

    public ResultsBean(Double value, String operation, String category, String resolution) {
        this.value = value;
        this.category = category;
        this.operation = operation;
        this.resolution = resolution;
    }

    public List<List<String>> getDescription() {
        return description;
    }

    public void setDescription(List<List<String>> description) {
        this.description = description;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public List<String> getRatio() {
        return ratio;
    }

    public void setRatio(List<String> ratio) {
        this.ratio = ratio;
    }

    public Double getValue() {
        return value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
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

    /**
     * List characteristics of position, distance, reshift, luminosity, metallicity and relative error
     * of galaxy specified
     */
    public void fillResultsForGalaxyDescription() {

        Integer n = description.size();
        Integer iter = 0;
        results = "";

        while (iter < n) {
            results += "<tr>\n<td>"
                    + description.get(iter).get(0) +
                    "</td>\n" +
                    "<td>\n";

            String pos[] = {description.get(iter).get(1),
                    description.get(iter).get(2),
                    description.get(iter).get(3),
                    description.get(iter).get(4),
                    description.get(iter).get(5),
                    description.get(iter).get(6),
                    description.get(iter).get(7),
                    description.get(iter).get(8),
                    description.get(iter).get(9),
                    description.get(iter).get(10),
                    description.get(iter).get(11),
                    description.get(iter).get(12)};
            Integer i = 0;
            while (i < pos.length) {
                if (pos[i].compareTo("-1") == 0 || pos[i].charAt(0) == '*') {
                    pos[i] = "-";
                }
                i++;
            }
            results += "A[" + pos[0] + "," + pos[1] + "," + pos[2] + "]-" +
                    "D[" + pos[3] + "," + pos[4] + "," + pos[5] + "," + pos[6] + "]</td>\n" +
                    "<td>" + pos[7] + " [" + pos[8] + "]</td>" +
                    "<td>" + pos[9] + " [" + pos[10] + "]</td>" +
                    "<td>" + pos[11] + "</td>";
            results += "</tr>";
            iter++;
        }
    }

    /**
     * List into table rows, tuple of result of the values of line flux founded for the galaxy specified
     */
    public void fillResultsForGalaxySpectralLines() {

        Integer n = description.size();
        Integer iter = 0;
        results = "";
        while (iter < n) {
            results += "<tr>\n" +
                    "<td>"+ description.get(iter).get(0) + "</td>\n" +
                    "<td>\n" + description.get(iter).get(1) + "</td>";
            if (!description.get(iter).get(2).equals("-1") ){
                results += "<td>"+ description.get(iter).get(2) + "</td>\n";
            } else {
                results += "<td>-</td>\n";
            }
            if (!description.get(iter).get(3).equals("-1") ){
                results += "<td>"+ description.get(iter).get(3) + "</td>\n";
            } else {
                results += "<td>-</td>\n";
            }
            if (!description.get(iter).get(4).equals("*") ){
                results += "<td>y</td>\n";
            } else {
                results += "<td>n</td>\n";
            }
            iter++;
        }
    }

    /**
     * Show value of ratio between two flux and if it is an upperlimit of lowerlimit
     */
    public void fillResultsForTwoFluxesRatio() {

        results = "<td>" + ratio.get(0) + "</td>";
        if ( !ratio.get(1).equals("-")) {
            results = results.concat("<td>Upper limit</td>");
        } else if (!ratio.get(2).equals("*") && !ratio.get(2).equals("-")) {
            results = results.concat("<td>Lower limit</td>");
        } else {
            results = results.concat("<td>-</td>");
        }

    }

    /**
     * List into table rows, tuple of result of the statistics operation chosen
     */
    public void fillResultsForStatistics() {

        results = "<td>";
        results = results.concat(category);
        results = results.concat("</td><td>");
        if (operation.equals("avg"))
            results = results.concat("Average value");
        else if (operation.equals("astd"))
            results = results.concat("Absolute average standard deviation");
        else if (operation.equals("std"))
            results = results.concat("Standard deviation");
        else if (operation.equals("med"))
            results = results.concat("Median value");
        results = results.concat("</td><td>");
        results = results.concat(value.toString());
        results = results.concat("</td><td>");
        if ( resolution.length() != 0) {
            results = results.concat(resolution);
        } else {
            results = results.concat("-");
        }
        results = results.concat("</td>");
    }

    /**
     * List name of file imported into system
     */
    public void getListOfFiles() {

        CSVManager cm = new CSVManager();
        List<String> files = cm.getAllFiles();

        results = "";
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
