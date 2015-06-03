package de.appdynamics.ace.report.jasperreports.amql.execution;

/**
 * Created by stefan.marx on 06.03.15.
 */
public abstract class BaseFileExporter implements ReportExporter{
    private String _filename;

    public BaseFileExporter(String filename) {
        setFilename(filename);

    }

    public void setFilename(String filename) {
        _filename = filename;
    }

    public String getFilename() {
        return _filename;
    }
}
