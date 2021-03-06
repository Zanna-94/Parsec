package it.uniroma2.dicii.bdc.parsec.view;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;

import java.util.HashMap;

public class ratioFluxBean {

    private String lineFlux;

    private String galaxyName;

    private HashMap<String, Double> ratio;

    public boolean calculate() {

        if (this.lineFlux == null)
            return false;

        StatisticsQueryController controller = new StatisticsQueryController();
        this.ratio = controller.calculateRatio(lineFlux, galaxyName);

        if (this.ratio != null)
            return true;

        return false;
    }

    public String getLineFlux() {
        return lineFlux;
    }

    public void setLineFlux(String lineFlux) {
        this.lineFlux = lineFlux;
    }

    public String getGalaxyName() {
        return galaxyName;
    }

    public void setGalaxyName(String galaxyName) {
        this.galaxyName = galaxyName;
    }

    public HashMap<String, Double> getRatio() {
        return ratio;
    }

    public void setRatio(HashMap<String, Double> ratio) {
        this.ratio = ratio;
    }

    public int getSize() {
        if (ratio != null)
            return ratio.size();
        return 0;
    }

}
