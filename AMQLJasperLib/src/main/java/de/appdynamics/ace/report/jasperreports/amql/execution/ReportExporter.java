package de.appdynamics.ace.report.jasperreports.amql.execution;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Created by stefan.marx on 06.03.15.
 */
public interface ReportExporter {
    void exportReport(JasperPrint prn) throws ReportExportException;
}
