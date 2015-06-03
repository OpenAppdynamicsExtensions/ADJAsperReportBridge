package de.appdynamics.ace.report.jasperreports.amql.execution;


import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by stefan.marx on 06.03.15.
 */
public class ReportExecutionEnvironment {
    final static Logger logger = Logger.getLogger(ReportExecutionEnvironment.class.getName());



    private ADDataAdapter _data;

    public ReportExecutionEnvironment(ADDataAdapter adDataAdapter) {
        setData(adDataAdapter);

    }

    public void setData(ADDataAdapter data) {
        _data = data;
    }

    public ADDataAdapter getData() {
        return _data;
    }

    public void executeCompiledReport(String reportFile, ReportExporter pdfExporter) {
        try {
            JasperReport rpt = (JasperReport) JRLoader.loadObjectFromFile(reportFile);

        } catch (JRException e) {
            logger.log(Level.SEVERE,"Error while executing Report !",e);
        }

    }
}
