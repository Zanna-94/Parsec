package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.model.dao.CSV_DAO;
import it.uniroma2.dicii.bdc.parsec.view.ImportForm;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class REQFN_3_4_ImportCSVFileTest {

    /**
     * REQ-FN-3A: Import data of a CSV file into database
     * @throws IOException
     */
    @Test
    public void importNewCSVFile() throws IOException {

        String file1 = "MRTable3_sample.csv";
        String file2 = "MRTable8_irs.csv";
        String file3 = "MRTable4_flux.csv";
        String file4 = "MRTable6_cont.csv";
        String file5 = "MRTable11_C_3x3_5x5_flux.csv";

        ImportForm f = new ImportForm();
        CSVManager c = new CSVManager();

        f.setFilename(file1);
        c.setFilename(f.getFilename());

        c.saveNewFile(f);

        f.setFilename(file2);
        c.setFilename(f.getFilename());

        c.saveNewFile(f);

        f.setFilename(file3);
        c.setFilename(f.getFilename());

        c.saveNewFile(f);

        f.setFilename(file4);
        c.setFilename(f.getFilename());

        c.saveNewFile(f);

        f.setFilename(file5);
        c.setFilename(f.getFilename());

        c.saveNewFile(f);

        assert CSV_DAO.searchFile(file1).get(0).getName().equals(file1);
        assert CSV_DAO.searchFile(file2).get(0).getName().equals(file2);
        assert CSV_DAO.searchFile(file3).get(0).getName().equals(file3);
        assert CSV_DAO.searchFile(file4).get(0).getName().equals(file4);
        assert CSV_DAO.searchFile(file5).get(0).getName().equals(file5);
    }
}
