package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.model.dao.CSVManager;

import java.io.IOException;

public class DemoImportCSV {

    public static void main(String args[]) {

        String file1 = "MRTable8_irs.csv";
        String file2 = "MRTable4_flux.csv";
        String file3 = "MRTable6_cont.csv";
        String file4 = "MRTable3_sample.csv";
        String file5 = "MRTable11_C_3x3_5x5_flux.csv";

        CSVManager csvManager = new CSVManager();
        try {
            /*csvManager.importFile(file1);
            csvManager.importFile(file2);
            csvManager.importFile(file3);*/
            csvManager.importFile(file4);
            csvManager.importFile(file5);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

    }

}
