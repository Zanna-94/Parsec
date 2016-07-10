package it.uniroma2.dicii.bdc.parsec;


import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.controller.QueryController;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.view.ImportForm;

import java.io.IOException;
import java.util.List;

public class DemoStatistics {

    public static void main(String args[]) throws IOException {

        QueryController q = new QueryController();

        String category = "Dwarf";
        String resolution = "c";

        ImportForm file = new ImportForm();
        file.setFilename("MRTable8_irs.csv");
        CSVManager cm = new CSVManager(file.getFilename());
        cm.saveNewFile(file);

        List<Float> fluxes = FluxDAO.findLinesByCategory(category);
        System.out.printf("values %s\n", fluxes);

        if (fluxes == null) {
            System.out.printf("No values\n");
        } else {
            System.out.printf("avg %f\n", q.averageLinesRatioValues(category));
            /*System.out.printf("med %f\n", q.medianLinesRatioValues(category));
            System.out.printf("std %f\n", q.standarDeviationLinesRatioValues(category));
            System.out.printf("astd %f\n", q.averageAbsoluteDeviationLinesRatioValues(category));*/
        }

        /*List<Flux> fluxesByAperture = FluxDAO.findLinesByCategoryAndAperture(category, resolution);

        if (fluxesByAperture == null) {
            System.out.printf("No values\n");
        } else {
            System.out.printf("avg aperture %f\n", q.averageLinesRatioValuesByAperture(category, resolution));
            System.out.printf("med aperture %f\n", q.medianLinesRatioValuesByAperture(category, resolution));
            System.out.printf("std aperture %f\n", q.standarDeviationLinesRatioValuesByAperture(category, resolution));
            System.out.printf("astd aperture %f\n", q.averageAbsoluteDeviationLinesRatioValuesByAperture(category, resolution));
        }*/
        System.exit(0);
    }
}
