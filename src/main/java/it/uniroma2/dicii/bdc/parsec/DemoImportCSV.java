package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.view.ImportForm;

import java.io.IOException;

public class DemoImportCSV {

    public static void main(String args[]) throws IOException {

        String file1 = "MRTable8_irs.csv";
        String file2 = "MRTable4_flux.csv";
        String file3 = "MRTable6_cont.csv";
        String file4 = "MRTable3_sample.csv";
        String file5 = "MRTable11_C_3x3_5x5_flux.csv";


        ImportForm f1 = new ImportForm();
        f1.setFilename(file1);
        CSVManager c1 = new CSVManager(f1.getFilename());

        ImportForm f2 = new ImportForm();
        f2.setFilename(file2);
        CSVManager c2 = new CSVManager(f2.getFilename());

        ImportForm f3 = new ImportForm();
        f3.setFilename(file3);
        CSVManager c3 = new CSVManager(f3.getFilename());

        ImportForm f4 = new ImportForm();
        f4.setFilename(file4);
        CSVManager c4 = new CSVManager(f4.getFilename());


        ImportForm f5 = new ImportForm();
        f5.setFilename(file5);
        CSVManager c5 = new CSVManager(f5.getFilename());

        try {
            //c5.saveNewFile(f5);
            //c1.saveNewFile(f1);
            //c2.saveNewFile(f2);
            c3.saveNewFile(f3);
            //c4.saveNewFile(f4);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

    }

}
