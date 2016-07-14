package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.model.CSVFile;
import it.uniroma2.dicii.bdc.parsec.model.dao.CSV_DAO;
import it.uniroma2.dicii.bdc.parsec.view.ImportForm;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class REQFN_3_4_ImportCSVFileTest {

    /**
     * REQ-FN-3A: Import data of a CSV file into database.
     *            Test results passed if file imported is is update list of
     *            file into database table Files.
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

        /*  file that has to be memorized into Database */
        List<CSVFile> dbFile1 = CSV_DAO.searchFile(file1);
        List<CSVFile> dbFile2 = CSV_DAO.searchFile(file2);
        List<CSVFile> dbFile3 = CSV_DAO.searchFile(file3);
        List<CSVFile> dbFile4 = CSV_DAO.searchFile(file4);
        List<CSVFile> dbFile5 = CSV_DAO.searchFile(file5);


        assertEquals(dbFile1.get(0).getName(), file1);
        assertEquals(dbFile2.get(0).getName(), file2);
        assertEquals(dbFile3.get(0).getName(), file3);
        assertEquals(dbFile4.get(0).getName(), file4);
        assertEquals(dbFile5.get(0).getName(), file5);
    }
}
