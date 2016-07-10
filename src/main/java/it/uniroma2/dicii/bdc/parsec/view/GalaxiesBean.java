package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.QueryController;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;

import java.util.List;

/**
 * JavaBean contains entity results from a query.
 *
 * @see ResearchRedshiftBean
 * @see Galaxy
 */
public class GalaxiesBean {


    public GalaxiesBean(List<Galaxy> galaxies) {
        this.galaxies = galaxies;
    }

    /**
     * {@link Galaxy} sorted by redshift
     * in ascending order
     */
    private List<Galaxy> galaxies;

    private List<String> galaxyName;

    public void populate() {

        galaxyName = GalaxyDAO.findAllName();

    }

    public String getName(int index) {
        return galaxies.get(index).getName();
    }

    public String getAlterName(int index) {
        return galaxies.get(index).getAlterName();
    }

    public String getCategory(int index) {
        return galaxies.get(index).getCategory();
    }

    public String getRedshift(int index) {
        return galaxies.get(index).getPosition().getRedshift().toString();
    }

    public int getSize() {
        return galaxies.size();
    }

    public List<String> getGalaxyName() {
        return galaxyName;
    }

    public void setGalaxyName(List<String> galaxyName) {
        this.galaxyName = galaxyName;
    }
}
