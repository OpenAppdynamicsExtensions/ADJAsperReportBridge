package de.appdynamics.ace.report.jasperreports.amql.execution;

/**
 * Created by stefan.marx on 06.03.15.
 */
public class ReportExportException extends Exception {
    public ReportExportException(String msg) {
           super(msg);
    }
    public ReportExportException(String msg,Throwable t) {
        super(msg,t);
    }
}
