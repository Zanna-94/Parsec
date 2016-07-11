package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean contains all information required from the view, about {@link Galaxy},
 * In general it contains information about a list of Galaxies.
 * * @see Galaxy
 */
public class GalaxiesBean {


    public GalaxiesBean() {
    }

    public GalaxiesBean(List<Galaxy> galaxies) {

        if (galaxies != null)
            for (Galaxy g : galaxies) {
                this.galaxyName.add(g.getName());
                this.alterName.add(g.getAlterName());
                this.category.add(g.getCategory());

                if (g.getPosition() != null)
                    this.redshift.add(g.getPosition().getRedshift().toString());
            }
    }

    private List<String> galaxyName = new ArrayList<String>();

    private List<String> alterName = new ArrayList<String>();

    private List<String> category = new ArrayList<String>();

    private List<String> galaxyAlternName = new ArrayList<String>();

    private List<String> redshift = new ArrayList<String>();

    public void populate() {

        galaxyName = GalaxyDAO.findAllName();

    }

    public String getName(int index) {
        return galaxyName.get(index);
    }

    public String getAlterName(int index) {
        return alterName.get(index);
    }

    public String getCategory(int index) {
        return category.get(index);
    }

    public String getRedshift(int index) {
        return redshift.get(index);
    }

    public int getSize() {
        return galaxyName.size();
    }

    public List<String> getGalaxyName() {
        return galaxyName;
    }

    public void setGalaxyName(List<String> galaxyName) {
        this.galaxyName = galaxyName;
    }

    public void setAlterName(List<String> alterName) {
        this.alterName = alterName;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void setGalaxyAlternName(List<String> galaxyAlternName) {
        this.galaxyAlternName = galaxyAlternName;
    }

    public void setRedshift(List<String> redshift) {
        this.redshift = redshift;
    }

    public List<String> getAlterName() {
        return alterName;
    }

    public List<String> getCategory() {
        return category;
    }

    public List<String> getGalaxyAlternName() {
        return galaxyAlternName;
    }

    public List<String> getRedshift() {
        return redshift;
    }
}
