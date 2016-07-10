package it.uniroma2.dicii.bdc.parsec.view;


import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;
import it.uniroma2.dicii.bdc.parsec.model.Ascension;
import it.uniroma2.dicii.bdc.parsec.model.Declination;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;

import java.util.List;

public class PositionBean {

    private Integer ascensionHour;

    private Integer ascensionMin;

    private Float ascensionSec;

    private Integer declinationDeg;

    private Integer declinationMin;

    private Float declinationSec;

    private Integer howMany; // indicates how many galaxies have to be returned


    public GalaxiesBean calculate() {
        StatisticsQueryController controller = new StatisticsQueryController();

        Ascension ascension = new Ascension(ascensionHour, ascensionMin, ascensionSec);
        //Assume that the sign is included in declinatinDeg (the only value that can be negative)
        Declination declination = new Declination(null, declinationDeg, declinationMin, declinationSec);

        List<Galaxy> galaxies = controller.searchInRange(ascension, declination, howMany);

        return new GalaxiesBean(galaxies);
    }

    public Integer getAscensionHour() {
        return ascensionHour;
    }

    public void setAscensionHour(Integer ascensionHour) {
        this.ascensionHour = ascensionHour;
    }

    public Integer getAscensionMin() {
        return ascensionMin;
    }

    public void setAscensionMin(Integer ascensionMin) {
        this.ascensionMin = ascensionMin;
    }

    public Float getAscensionSec() {
        return ascensionSec;
    }

    public void setAscensionSec(Float ascensionSec) {
        this.ascensionSec = ascensionSec;
    }

    public Integer getDeclinationDeg() {
        return declinationDeg;
    }

    public void setDeclinationDeg(Integer declinationDeg) {
        this.declinationDeg = declinationDeg;
    }

    public Integer getDeclinationMin() {
        return declinationMin;
    }

    public void setDeclinationMin(Integer declinationMin) {
        this.declinationMin = declinationMin;
    }

    public Float getDeclinationSec() {
        return declinationSec;
    }

    public void setDeclinationSec(Float declinationSec) {
        this.declinationSec = declinationSec;
    }


    public Integer getHowMany() {
        return howMany;
    }

    public void setHowMany(Integer howMany) {
        this.howMany = howMany;
    }
}
