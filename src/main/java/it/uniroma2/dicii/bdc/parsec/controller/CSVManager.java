package it.uniroma2.dicii.bdc.parsec.controller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.model.dao.*;
import it.uniroma2.dicii.bdc.parsec.view.ImportForm;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.Random;


/**
 * Manage importation of new CSV file, loading data into database.
 * <p/>
 * Recognized files' format listed into Format enumeration.
 */
public class CSVManager {

    private String filename;

    private static String tmpFile = "tmp";

    /**
     * Enumeration of default CSV file formats.
     */
    private enum Format {
        SAMPLE("sample", 3),
        FLUX1("flux", 4),
        FLUX2("flux", 11),
        CONT("cont", 6),
        IRS("irs", 8);

        private String name;
        private int num;

        public String getFormatName() {
            return name;
        }

        public int getFormatNum() {
            return num;
        }

        Format(String name, int num) {
            this.name = name;
            this.num = num;
        }
    }

    /**
     * Default constructor
     */
    public CSVManager() {
    }

    public CSVManager(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Writing a unique temporary file to compose a CSV file, based on a parsable format
     *
     * @param delimiter  char as delimiter of data in the CSV file
     * @param fileFormat type of CSV file to read
     * @return path of the new CSV formatted file
     * @throws IOException
     */
    private String composeFileToParse(Character delimiter, Integer fileFormat)
            throws IOException {
        /*  creating unique temporary file to write csv formatted file */
        Random r = new Random();
        Integer random = r.nextInt(100000 - 1) + 1;

        // Buld temporany file's name
        tmpFile = tmpFile.concat(fileFormat.toString());
        tmpFile = tmpFile.concat(random.toString());

        // Create temporany file
        File temp = File.createTempFile("tempfile", ".tmp");

        FileWriter w = new FileWriter(temp, true);
        String header = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));

        Long indexCols = 0L;
        String line;
        int i = 1;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("----")) {
                i++;
            } else {
                if (i == 2) {
                    indexCols = line.indexOf("Label") - 2L;
                }
                if (i == 3) {
                    /* read columns names   */
                    StringReader sr = new StringReader(line);
                    sr.skip(indexCols);
                    Character c;
                    while ((c = (char) sr.read()) == ' ') {
                    }
                    header += c;
                    while ((c = (char) sr.read()) != ' ') {
                        header += c;
                    }
                    header += delimiter;
                } else if (
                        i > 4 ||
                                (!line.startsWith("Note") && !line.startsWith("References") && line.length() == 1
                                        && !line.startsWith("     ") && !line.startsWith("   Bytes"))) {
                    break;
                }
            }
        }

        if (!header.startsWith("Name"))
            header = header.substring(2, header.length() - 1);
        else
            header = header.substring(0, header.length() - 1);

        w.write(header);
        w.write('\n');

        while ((line = br.readLine()) != null) {
            w.write(line);
            w.write('\n');
        }

        br.close();

        w.close();

        return temp.getPath();
    }

    /**
     * Read CSV file specified in input and insert data into database
     *
     * @param name file to parse
     * @throws IOException
     */
    public Boolean importFile(String name) throws IOException {

        // Obtain file from resources
        ClassLoader classLoader = getClass().getClassLoader();
        File oldFile = new File(classLoader.getResource("files/" + name).getFile());

        // file's format
        Integer fileFormat = readFormat(name);

        // Obtain delimiter
        Character delimiter = getDelimiterByFormat(fileFormat);

        // compose new file without header and references
        String f = composeFileToParse(delimiter, fileFormat);
        FileReader file = new FileReader(f);

        CSVFormat format = CSVFormat.EXCEL.withDelimiter(delimiter).withHeader();

        CSVParser parser = new CSVParser(file, format);
        Iterable<CSVRecord> records = parser.getRecords();

        records.iterator();
        for (CSVRecord record : records) {
            /* add record's data in db    */
            saveRecordInDB(record, fileFormat);
            records.iterator();
        }
        return true;
    }

    /**
     * Save into database record's data, reading them in according to format of file
     *
     * @param record     line of CSV file
     * @param fileFormat type of format of CSV file
     * @throws IOException
     */
    private void saveRecordInDB(CSVRecord record, Integer fileFormat) throws IOException {

        /*  sample file (SAMPLE) */
        if (fileFormat == 3) {
            /* update 'galaxy' table  */
            insertGalaxyInfo(record);
            /* update 'metallicity' table   */
            insertMetallicity(record);
            /* update 'luminosity' table    */
            insertLuminosity(record);

        /*  others file formats */
        } else {
            /*  flux file (C_3X3_5X5_FLUX) equals to flux file FLUX */
            if (fileFormat == 11) {
                fileFormat = 4;
            }
            /*  update 'flux' table */
            insertFlux(record, fileFormat);
        }
    }

    /**
     * Update of 'flux' table into database reading info contained in the record
     *
     * @param record     line of CSV file
     * @param fileFormat type of CSV file format
     */
    private void insertFlux(CSVRecord record, Integer fileFormat) {

        CSVManager m = new CSVManager();

        Galaxy refGalaxy = new Galaxy(m.getValue(record, "Name"));
        GalaxyDAO.store(refGalaxy);

        switch (fileFormat) {
            /*  flux file (FLUX1, FLUX2) */
            case 4:

                Flux f14 = new Flux(refGalaxy,
                        "OIII52",
                        Float.parseFloat(m.getValue(record, "Foiii52")),
                        m.getValue(record, "l_Foiii52").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Foiii52")),
                        m.getValue(record, "Aper"),
                        "l");

                Flux f24 = new Flux(refGalaxy,
                        "NIII57",
                        Float.parseFloat(m.getValue(record, "Fniii57")),
                        m.getValue(record, "l_Fniii57").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fniii57")),
                        m.getValue(record, "Aper"),
                        "l");

                Flux f34 = new Flux(refGalaxy,
                        "OI63",
                        Float.parseFloat(m.getValue(record, "Foi63")),
                        m.getValue(record, "l_Foi63").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Foi63")),
                        m.getValue(record, "Aper"),
                        "l");

                Flux f44 = new Flux(refGalaxy,
                        "OIII88",
                        Float.parseFloat(m.getValue(record, "Foiii88")),
                        m.getValue(record, "l_Foiii88").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Foiii88")),
                        m.getValue(record, "Aper"),
                        "l");
                Flux f54 = new Flux(refGalaxy,
                        "NII122",
                        Float.parseFloat(m.getValue(record, "Fnii122")),
                        m.getValue(record, "l_Fnii122").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fnii122")),
                        m.getValue(record, "Aper"),
                        "l");

                Flux f64 = new Flux(refGalaxy,
                        "OI145",
                        Float.parseFloat(m.getValue(record, "Foi145")),
                        m.getValue(record, "l_Foi145").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Foi145")),
                        m.getValue(record, "Aper"),
                        "l");

                Flux f74 = new Flux(refGalaxy,
                        "CII158",
                        Float.parseFloat(m.getValue(record, "Fcii158")),
                        m.getValue(record, "l_Fcii158").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fcii158")),
                        m.getValue(record, "Aper"),
                        "l");

                FluxDAO.store(f14);
                FluxDAO.store(f24);
                FluxDAO.store(f34);
                FluxDAO.store(f44);
                FluxDAO.store(f54);
                FluxDAO.store(f64);
                FluxDAO.store(f74);

                break;
            /*  continuous flux file (CONT) */
            case 6:

                Flux f16 = new Flux(refGalaxy,
                        "OIII52",
                        Float.parseFloat(m.getValue(record, "Coiii52")),
                        '-',
                        Float.parseFloat(m.getValue(record, "e_Coiii52")),
                        m.getValue(record, "Aper"),
                        "c");

                Flux f26 = new Flux(refGalaxy,
                        "NIII57",
                        Float.parseFloat(m.getValue(record, "Cniii57")),
                        '-',
                        Float.parseFloat(m.getValue(record, "e_Cniii57")),
                        m.getValue(record, "Aper"),
                        "c");

                Flux f36 = new Flux(refGalaxy,
                        "OI63",
                        Float.parseFloat(m.getValue(record, "Coi63")),
                        m.getValue(record, "l_Coi63").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Coi63")),
                        m.getValue(record, "Aper"),
                        "c");

                Flux f46 = new Flux(refGalaxy,
                        "OIII88",
                        Float.parseFloat(m.getValue(record, "Coiii88")),
                        m.getValue(record, "l_Coiii88").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Coiii88")),
                        m.getValue(record, "Aper"),
                        "c");

                Flux f56 = new Flux(refGalaxy,
                        "NII122",
                        Float.parseFloat(m.getValue(record, "Cnii122")),
                        m.getValue(record, "l_Cnii122").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Cnii122")),
                        m.getValue(record, "Aper"),
                        "c");

                Flux f66 = new Flux(refGalaxy,
                        "OI145",
                        Float.parseFloat(m.getValue(record, "Coi145")),
                        m.getValue(record, "l_Coi145").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Coi145")),
                        m.getValue(record, "Aper"),
                        "c");

                Flux f76 = new Flux(refGalaxy,
                        "CII158",
                        Float.parseFloat(m.getValue(record, "Ccii158")),
                        m.getValue(record, "l_Ccii158").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Ccii158")),
                        m.getValue(record, "Aper"),
                        "c");

                FluxDAO.store(f16);
                FluxDAO.store(f26);
                FluxDAO.store(f36);
                FluxDAO.store(f46);
                FluxDAO.store(f56);
                FluxDAO.store(f66);
                FluxDAO.store(f76);

                break;
            /*  irs file (IRS) */
            case 8:

                Flux f18 = new Flux(refGalaxy,
                        "SIV10.5",
                        Float.parseFloat(m.getValue(record, "Fsiv10")),
                        m.getValue(record, "l_Fsiv10").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fsiv10")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f28 = new Flux(refGalaxy,
                        "NeII12.8",
                        Float.parseFloat(m.getValue(record, "Fneii12")),
                        m.getValue(record, "l_Fneii12").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fneii12")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f38 = new Flux(refGalaxy,
                        "NeV14.3",
                        Float.parseFloat(m.getValue(record, "Fnev14")),
                        m.getValue(record, "l_Fnev14").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fnev14")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f48 = new Flux(refGalaxy,
                        "NeIII15.6",
                        Float.parseFloat(m.getValue(record, "Fneiii15")),
                        m.getValue(record, "l_Fneiii15").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fneiii15")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f58 = new Flux(refGalaxy,
                        "SIII18",
                        Float.parseFloat(m.getValue(record, "Fsiii18")),
                        m.getValue(record, "l_Fsiii18").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fsiii18")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f68 = new Flux(refGalaxy,
                        "NeV24.3",
                        Float.parseFloat(m.getValue(record, "Fnev24")),
                        m.getValue(record, "l_Fnev24").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fnev24")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f78 = new Flux(refGalaxy,
                        "OIV25.9",
                        Float.parseFloat(m.getValue(record, "Foiv25")),
                        m.getValue(record, "l_Foiv25").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Foiv25")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f88 = new Flux(refGalaxy,
                        "SIII33.5",
                        Float.parseFloat(m.getValue(record, "Fsiii33")),
                        m.getValue(record, "l_Fsiii33").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fsiii33")),
                        m.getValue(record, "Mod"),
                        "l");

                Flux f98 = new Flux(refGalaxy,
                        "SII34.8",
                        Float.parseFloat(m.getValue(record, "Fsii34")),
                        m.getValue(record, "l_Fsii34").charAt(0),
                        Float.parseFloat(m.getValue(record, "e_Fsii34")),
                        m.getValue(record, "Mod"),
                        "l");

                FluxDAO.store(f18);
                FluxDAO.store(f28);
                FluxDAO.store(f38);
                FluxDAO.store(f48);
                FluxDAO.store(f58);
                FluxDAO.store(f68);
                FluxDAO.store(f78);
                FluxDAO.store(f88);
                FluxDAO.store(f98);

                break;
            default:
                break;
        }
    }

    /**
     * Update of 'metallicity' table into database reading info contained in the record
     *
     * @param record line of CSV file
     */
    private void insertMetallicity(CSVRecord record) {

        CSVManager m = new CSVManager();

        Galaxy refGalaxy = new Galaxy(m.getValue(record, "Name"));
        GalaxyDAO.store(refGalaxy);

        Metallicity metallicity = new Metallicity();
        metallicity.setGalaxy(refGalaxy);
        metallicity.setValue(Float.parseFloat(m.getValue(record, "Z")));
        metallicity.setError(Float.parseFloat(m.getValue(record, "e_Z")));

        MetallicityDAO.store(metallicity);
    }

    /**
     * Update of 'luminosity' table into database reading info contained in the record
     *
     * @param record line of CSV file
     */
    private void insertLuminosity(CSVRecord record) {

        CSVManager m = new CSVManager();

        Galaxy refGalaxy = new Galaxy(m.getValue(record, "Name"));
        GalaxyDAO.store(refGalaxy);

        Luminosity lum1 = new Luminosity();
        lum1.setGalaxy(refGalaxy);
        lum1.setAtom("NeV14.3");
        lum1.setValue(Float.parseFloat(m.getValue(record, "Lnev1")));
        lum1.setUpperLimit(m.getValue(record, "l_Lnev1").charAt(0));

        Luminosity lum2 = new Luminosity();
        lum2.setGalaxy(refGalaxy);
        lum2.setAtom("NeV24.3");
        lum2.setValue(Float.parseFloat(m.getValue(record, "Lnev2")));
        lum2.setUpperLimit(m.getValue(record, "l_Lnev2").charAt(0));

        Luminosity lum3 = new Luminosity();
        lum3.setGalaxy(refGalaxy);
        lum3.setAtom("OIV25.9");
        lum3.setValue(Float.parseFloat(m.getValue(record, "Loiv")));
        lum3.setUpperLimit(m.getValue(record, "l_Loiv").charAt(0));

        LuminosityDAO.store(lum1);
        LuminosityDAO.store(lum2);
        LuminosityDAO.store(lum3);
    }

    /**
     * Update of 'galaxy' table into database reading info contained in the record
     *
     * @param record line of CSV file
     * @throws IOException
     */
    private void insertGalaxyInfo(CSVRecord record) throws IOException {

        CSVManager m = new CSVManager();
        Galaxy galaxy = new Galaxy();

        Declination declination = new Declination(m.getValue(record, "DE-").charAt(0),
                Integer.parseInt(m.getValue(record, "DEm")),
                Integer.parseInt(m.getValue(record, "DEd")),
                Float.parseFloat(m.getValue(record, "DEs")));

        Ascension ascension = new Ascension(Integer.parseInt(m.getValue(record, "RAh")),
                Integer.parseInt(m.getValue(record, "RAm")),
                Float.parseFloat(m.getValue(record, "RAs")));

        Position posGalaxy = new Position(Float.parseFloat(m.getValue(record, "D")),
                Float.parseFloat(m.getValue(record, "z")),
                declination,
                ascension);

        galaxy.setName(m.getValue(record, "Name"));

        galaxy.setAlterName(m.getValue(record, "AName"));

        galaxy.setCategory(m.getValue(record, "Sp"));

        galaxy.setPosition(posGalaxy);

        GalaxyDAO.store(galaxy);
    }

    /**
     * Get the value of column into record without empty characters
     *
     * @param record line of CSV file
     * @param col    name of column of CSV file
     * @return string containing value of column specified
     */
    private String getValue(CSVRecord record, String col) {

        String s = record.get(col);

        int i = 0, len = s.length();

        if (len != 0) {
            while (i < len) {
                if (s.charAt(i) == ' ')
                    i++;
                else
                    break;
            }

            int j = len;
            while (j > i) {
                if (s.charAt(j - 1) == ' ')
                    j--;
                else
                    break;
            }

            s = s.substring(i, j);

            /*  if value of column is empty */
            if (s.length() == 0) {
                return "-1";
            }
            return s;
        }
        return "-1";
    }

    /**
     * Get delimiter associated to the CSV file format
     *
     * @param f file CSV format
     * @return character used as delimiter between columns
     */
    private Character getDelimiterByFormat(Integer f) {

        switch (f) {
            case 8:
                return ',';
            default:
                return ';';
        }
    }

    /**
     * Read format of file by filename
     *
     * @param s path of file of type '/path/file'
     * @return integer describing CSV file format
     */
    private Integer readFormat(String s) {
        Integer f = 0;

        for (Format i : Format.values()) {
            if (s.contains(Integer.toString(i.getFormatNum())) && s.contains(i.getFormatName())) {
                f = i.getFormatNum();
            }
        }
        return f;
    }



    public CSVFile saveNewFile(ImportForm form) {

        CSVFile file = new CSVFile();
        file.setName(form.getFilename());
        file.setFormat(readFormat(form.getFilename()));

        try {
            CSV_DAO.store(file);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

}
