package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.model.CSVFile;

import java.io.IOException;

/**
 * JavaBean contains information about the csv files that an administrator wants add to the System.
 */
public class ImportForm {

    private String filename;

    /**
     * Default constructor
     */
    public ImportForm() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return true if procedure end successful
     * @throws IOException if file not found
     * @see CSVManager#importFile()
     */
    public boolean validate() throws IOException {

        if (filename == null)
            return false;

        CSVManager fileManager = new CSVManager();
        // import csv file and saves filename
        CSVFile file = fileManager.saveNewFile(this);

        if (file == null) {
            return false;
        }

        return true;
    }
}
