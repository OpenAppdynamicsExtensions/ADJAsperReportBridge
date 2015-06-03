package de.appdynamics.ace.report.jasperreports.amql.test;

import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapter;
import de.appdynamics.ace.report.jasperreports.amql.execution.*;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by stefan.marx on 06.03.15.
 */
public class ExecutionTestCase extends TestCase {
    public void testPdfSimple() throws ReportExportException {

        ReportExecutionEnvironment env = new ReportExecutionEnvironment(new ADDataAdapter("demo2.appdynamics.com", "demouser", "apm13ad3r", "80"));
        env.executeCompiledReport("./src/test/resources/sample/Blank_A4.jasper",new PDFExporter("/tmp/report.pdf"));



    }
    public void testPdfSource() throws ReportExportException {

        ReportExecutionEnvironment env = new ReportExecutionEnvironment(new ADDataAdapter("demo2.appdynamics.com", "demouser", "apm13ad3r", "80"));
        env.executeSrcReport("./src/test/resources/sample/Blank_A4.jrxml", new PDFExporter("/tmp/report2.pdf"));

    }public void testCsvSource() throws ReportExportException {

        ReportExecutionEnvironment env = new ReportExecutionEnvironment(new ADDataAdapter("demo2.appdynamics.com", "demouser", "apm13ad3r", "80"));
        env.executeSrcReport("./src/test/resources/sample/Blank_A4.jrxml", new CSVExporter("/tmp/report2.csv"));

    }

    public void testPDFExport() throws ReportExportException {
        execute(new PDFExporter("/tmp/rep1.pdf"));

    }
    public void testHTMLExport() throws ReportExportException {
        execute(new HMTLExporter("/tmp/rep1.html"));

    }

    public void testXMLExport() throws ReportExportException {
        execute(new XMLExporter("/tmp/rep1.xml"));

    }

    private void execute(ReportExporter exporter) throws ReportExportException {
        ReportExecutionEnvironment env = new ReportExecutionEnvironment(new ADDataAdapter("demo2.appdynamics.com", "demouser", "apm13ad3r", "80"));
        env.executeCompiledReport("./src/test/resources/sample/Blank_A4.jasper",exporter);

    }
}
