package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.QueryController;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;

import java.util.ArrayList;

/**
 * JavaBean contains information entered by User.
 * This bean exchange message with {@link QueryController } to obtain the list of Galaxies
 * that have a value of redshift less or greater than specified one.
 *
 * @see GalaxiesBean
 */
public class ResearchRedshiftBean {

    /**
     * Value of redshift specified by the user
     */
    private Float redshift;

    /**
     * It indicates whether the search should be made on the redshift value lower than that specified
     */
    private boolean searchLower;

    public Float getRedshift() {
        return redshift;
    }

    public void setRedshift(Float redshift) {
        this.redshift = redshift;
    }

    public boolean isSearchLower() {
        return searchLower;
    }

    public void setSearchLower(boolean searchLower) {
        this.searchLower = searchLower;
    }

    public GalaxiesBean research() {
        QueryController controller = new QueryController();

        ArrayList<Galaxy> list = (ArrayList<Galaxy>) controller.searchForRedshift(redshift, searchLower);
        GalaxiesBean bean = new GalaxiesBean(list);

        return bean;
    }

}
