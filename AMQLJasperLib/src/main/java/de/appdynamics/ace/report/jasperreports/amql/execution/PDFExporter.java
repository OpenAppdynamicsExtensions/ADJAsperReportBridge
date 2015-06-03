package de.appdynamics.ace.report.jasperreports.amql.execution;

/**
 * Created by stefan.marx on 06.03.15.
 */
public class PDFExporter implements ReportExporter{
    private String _outPath;

    public PDFExporter(String outPath) {
        setOutPath(outPath);


    }

    public void setOutPath(String outPath) {
        _outPath = outPath;
    }

    public String getOutPath() {
        return _outPath;
    }
}
