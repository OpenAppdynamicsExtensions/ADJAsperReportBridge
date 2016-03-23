package de.appdynamics.ace.report.jasperreports.amql.execution;


import de.appdynamics.ace.metric.query.rest.ControllerRestAccess;
import de.appdynamics.ace.report.jasperreports.amql.adapter.ADAdapterConstants;
import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by stefan.marx on 06.03.15.
 */
public class ReportExecutionEnvironment {
    final static Logger logger = Logger.getLogger(ReportExecutionEnvironment.class.getName());
    private final Map<String, String> _map;


    private ADDataAdapter _data;

    public ReportExecutionEnvironment(ADDataAdapter adDataAdapter) {
        setData(adDataAdapter);
        _map = new HashMap<String,String>() ;
    }

    public ReportExecutionEnvironment(ADDataAdapter adDataAdapter, Map<String,String> map) {
        setData(adDataAdapter);
        _map = map ;
    }

    public void setParameter(String key, String value)  {
        _map.put(key,value);
    }

    public void setData(ADDataAdapter data) {
        _data = data;
    }

    public ADDataAdapter getData() {
        return _data;
    }

    public void executeCompiledReport(String reportFile, ReportExporter exporter) throws ReportExportException {
        try {
            JasperReport rpt = (JasperReport) JRLoader.loadObjectFromFile(reportFile);

            runAndExport(exporter, rpt);
        } catch (JRException e) {
           throw new ReportExportException("Error while executing Report !",e);
        }

    }

    private void runAndExport(ReportExporter exporter, JasperReport rpt) throws JRException, ReportExportException {
        Map<String, Object> param = new HashMap<String, Object>();

        ControllerRestAccess rest = new ControllerRestAccess(_data.getHost(), _data.getPort(), _data.isUseSSL(),
                _data.getUsername(), _data.getPassword(), _data.getAccount());

        param.put(ADAdapterConstants.CONNECTION,rest);

        param.putAll(_map);

        JasperPrint prn = JasperFillManager.fillReport(rpt, param);

        exporter.exportReport(prn);
    }

    public void executeSrcReport(String reportFile, ReportExporter exporter) throws ReportExportException {
      /* Compile the template */
        try {
            JasperReport rep = JasperCompileManager.compileReport(reportFile);
            runAndExport(exporter,rep);
        } catch (JRException e) {
            throw new ReportExportException("Error while compiling Report "+reportFile,e);
        }

    }
}
