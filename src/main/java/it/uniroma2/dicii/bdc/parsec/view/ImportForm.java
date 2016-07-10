package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.model.CSVFile;

import java.io.IOException;

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

    public boolean validate() throws IOException {

        if (filename == null)
            return false;

        CSVManager fileManager = new CSVManager();
        CSVFile file = fileManager.saveNewFile(this);

        if (file == null) {
            return false;
        }

        return true;
    }
}
