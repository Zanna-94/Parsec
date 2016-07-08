package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.model.dao.CSVManager;

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

        CSVManager cm = new CSVManager();

        if (!cm.importFile(filename)) {
            return false;
        }

        return true;
    }

}
