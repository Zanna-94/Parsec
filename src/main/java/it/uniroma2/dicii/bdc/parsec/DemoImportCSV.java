package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.view.ImportForm;

import java.io.IOException;

public class DemoImportCSV {

    public static void main(String args[]) {

        String file1 = "MRTable8_irs.csv";
        String file2 = "MRTable4_flux.csv";
        String file3 = "MRTable6_cont.csv";
        String file4 = "MRTable3_sample.csv";
        String file5 = "MRTable11_C_3x3_5x5_flux.csv";

        ImportForm f1 = new ImportForm();
        f1.setFilename(file1);
        ImportForm f2 = new ImportForm();
        f1.setFilename(file2);
        ImportForm f3 = new ImportForm();
        f1.setFilename(file3);
        ImportForm f4 = new ImportForm();
        f1.setFilename(file4);
        ImportForm f5 = new ImportForm();
        f1.setFilename(file5);

        CSVManager csvManager = new CSVManager();
        try {
            csvManager.saveNewFile(f1);
            csvManager.saveNewFile(f2);
            csvManager.saveNewFile(f3);
            csvManager.saveNewFile(f4);
            csvManager.saveNewFile(f5);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

    }

}
