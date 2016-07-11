package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class represents a file imported in the database.
 */
@Entity
@Table(name = "Files")
@SuppressWarnings("JpaDataSourceORMInspection")
public class CSVFile {

    @Id
    private String name;

    /**
     * file format (es. .csv)
     */
    private Integer format;

    public CSVFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

}
