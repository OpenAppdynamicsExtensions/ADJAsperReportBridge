package de.appdynamics.ace.report.jasperreports.amql.execution;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Created by stefan.marx on 06.03.15.
 */
public class PDFExporter extends BaseFileExporter{

    public PDFExporter(String outPath) {
        super(outPath);


    }


    @Override
    public void exportReport(JasperPrint prn) throws ReportExportException {
        try {
            JasperExportManager.exportReportToPdfFile(prn,getFilename());
        } catch (JRException e) {
            throw new ReportExportException("Export PDF failed to Path "+getFilename(),e);
        }
    }
}
