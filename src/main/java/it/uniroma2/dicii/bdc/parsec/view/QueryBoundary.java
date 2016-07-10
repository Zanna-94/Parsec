package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.QueryController;
import it.uniroma2.dicii.bdc.parsec.model.*;

import java.util.*;

/**
 * 
 */
public class QueryBoundary {

    private String galaxyName;

    private String fluxNum;
    private String fluxDen;

    private String resolution;
    private String operation;
    private String category;

    private Boolean atomOIV259;
    private Boolean atomNEV143;
    private Boolean atomNEV243;
    private Boolean atomOIII52;
    private Boolean atomNIII57;
    private Boolean atomOI63;
    private Boolean atomOIII88;
    private Boolean atomNII122;
    private Boolean atomOI145;
    private Boolean atomCII158;
    private Boolean atomSIV105;
    private Boolean atomNEII128;
    private Boolean atomNEIII156;
    private Boolean atomSIII187;
    private Boolean atomSIII335;
    private Boolean atomSII348;

    /**
     * Default constructor
     */
    public QueryBoundary() {
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFluxNum() {
        return fluxNum;
    }

    public void setFluxNum(String fluxNum) {
        this.fluxNum = fluxNum;
    }

    public String getFluxDen() {
        return fluxDen;
    }

    public void setFluxDen(String fluxDen) {
        this.fluxDen = fluxDen;
    }

    public String getGalaxyName() {
        return galaxyName;
    }

    public void setGalaxyName(String galaxyName) {
        this.galaxyName = galaxyName;
    }

    public Boolean isAtomOIV259() {
        return atomOIV259;
    }

    public void setAtomOIV259(Boolean atomOIV259) {
        this.atomOIV259 = atomOIV259;
    }

    public Boolean isAtomNEV143() {
        return atomNEV143;
    }

    public void setAtomNEV143(Boolean atomNEV143) {
        this.atomNEV143 = atomNEV143;
    }

    public Boolean isAtomNEV243() {
        return atomNEV243;
    }

    public void setAtomNEV243(Boolean atomNEV243) {
        this.atomNEV243 = atomNEV243;
    }

    public Boolean isAtomOIII52() {
        return atomOIII52;
    }

    public void setAtomOIII52(Boolean atomOIII52) {
        this.atomOIII52 = atomOIII52;
    }

    public Boolean isAtomNIII57() {
        return atomNIII57;
    }

    public void setAtomNIII57(Boolean atomNIII57) {
        this.atomNIII57 = atomNIII57;
    }

    public Boolean isAtomOI63() {
        return atomOI63;
    }

    public void setAtomOI63(Boolean atomOI63) {
        this.atomOI63 = atomOI63;
    }

    public Boolean isAtomOIII88() {
        return atomOIII88;
    }

    public void setAtomOIII88(Boolean atomOIII88) {
        this.atomOIII88 = atomOIII88;
    }

    public Boolean isAtomNII122() {
        return atomNII122;
    }

    public void setAtomNII122(Boolean atomNII122) {
        this.atomNII122 = atomNII122;
    }

    public Boolean isAtomOI145() {
        return atomOI145;
    }

    public void setAtomOI145(Boolean atomOI145) {
        this.atomOI145 = atomOI145;
    }

    public Boolean isAtomCII158() {
        return atomCII158;
    }

    public void setAtomCII158(Boolean atomCII158) {
        this.atomCII158 = atomCII158;
    }

    public Boolean isAtomSIV105() {
        return atomSIV105;
    }

    public void setAtomSIV105(Boolean atomSIV105) {
        this.atomSIV105 = atomSIV105;
    }

    public Boolean isAtomNEII128() {
        return atomNEII128;
    }

    public void setAtomNEII128(Boolean atomNEII128) {
        this.atomNEII128 = atomNEII128;
    }

    public Boolean isAtomNEIII156() {
        return atomNEIII156;
    }

    public void setAtomNEIII156(Boolean atomNEIII156) {
        this.atomNEIII156 = atomNEIII156;
    }

    public Boolean isAtomSIII187() {
        return atomSIII187;
    }

    public void setAtomSIII187(Boolean atomSIII187) {
        this.atomSIII187 = atomSIII187;
    }

    public Boolean isAtomSIII335() {
        return atomSIII335;
    }

    public void setAtomSIII335(Boolean atomSIII335) {
        this.atomSIII335 = atomSIII335;
    }

    public Boolean isAtomSII348() {
        return atomSII348;
    }

    public void setAtomSII348(Boolean atomSII348) {
        this.atomSII348 = atomSII348;
    }

    public ResultsBean getGalaxyByName() {

        if (galaxyName == null)
            return null;

        QueryController controller = new QueryController();

        Galaxy galaxy = controller.searchGalaxyByName(galaxyName);
        List<Metallicity> metallicities = controller.searchMetallicityByGalaxy(galaxyName);
        List<Luminosity> luminosities = controller.searchLuminosityByGalaxy(galaxyName);

        if (galaxy == null) {
            return null;
        }

         return new ResultsBean(galaxy, luminosities, metallicities);
    }

    public ResultsBean getGalaxySpectralLines() {

        if (galaxyName == null)
            return null;

        QueryController controller = new QueryController();

        List<Flux> fluxLines = controller.searchGalaxySpectralLines(this);

        if (fluxLines == null) {
            return null;
        }

        return new ResultsBean(fluxLines);
    }

    public ResultsBean getStatistics() {

        if (operation == null && resolution == null)
            return null;

        QueryController controller = new QueryController();

        Float value = controller.calculateStatistics(this);

        if (value == -1) {
            return null;
        }

        return new ResultsBean(value, operation, category, resolution);
    }

    public ResultsBean getAllGalaxySpectralLines() {

        if (galaxyName == null)
            return null;

        QueryController controller = new QueryController();

        List<Flux> fluxLines = controller.searchAllGalaxySpectralLines(this);

        if (fluxLines == null) {
            return null;
        }

        return new ResultsBean(fluxLines);
    }

    public ResultsBean getTwoLinesFluxRatio() {

        if (galaxyName == null || fluxNum == null || fluxDen == null)
            return null;

        QueryController controller = new QueryController();

        List<Flux> fluxLines = controller.searchGalaxySpectralLinesForRatio(this);

        if (fluxLines == null) {
            return null;
        }

        return new ResultsBean(fluxLines);
    }
}