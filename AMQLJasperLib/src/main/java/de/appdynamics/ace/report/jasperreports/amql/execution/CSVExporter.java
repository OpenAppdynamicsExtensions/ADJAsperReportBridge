package de.appdynamics.ace.report.jasperreports.amql.execution;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

/**
 * Created by stefan.marx on 06.03.15.
 */
public class CSVExporter extends BaseFileExporter{
    public CSVExporter(String s) {
        super(s)  ;
    }

    @Override
    public void exportReport(JasperPrint prn) throws ReportExportException {
        try {

            JRCsvExporter exporter = new JRCsvExporter();

            exporter.setExporterInput(new SimpleExporterInput(prn));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(getFilename()));

            exporter.exportReport();

        } catch (JRException e) {
            throw new ReportExportException("Export CSV failed to Path "+getFilename(),e);
        }
    }

}
