package de.appdynamics.ace.report.jasperreports.amql.execution;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

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

            JasperExportManager.exportReportToXmlFile(prn, getFilename(),true);
        } catch (JRException e) {
            throw new ReportExportException("Export XML failed to Path "+getFilename(),e);
        }
    }
}
