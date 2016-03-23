package de.appdynamics.jasperreport.cli;

import com.appdynamics.ace.util.cli.api.api.AbstractCommand;
import com.appdynamics.ace.util.cli.api.api.CommandException;
import com.appdynamics.ace.util.cli.api.api.OptionWrapper;
import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapter;
import de.appdynamics.ace.report.jasperreports.amql.execution.PDFExporter;
import de.appdynamics.ace.report.jasperreports.amql.execution.ReportExecutionEnvironment;
import de.appdynamics.ace.report.jasperreports.amql.execution.ReportExportException;
import org.apache.commons.cli.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefan.marx on 10.09.15.
 */
public class ReportCommand extends AbstractCommand {

    public static final String CONTROLLER = "controller";
    public static final String SSL = "ssl";
    public static final String PORT = "port";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ACCOUNT = "account";
    public static final String INFILE = "jasperfile";
    public static final String OUTFILE = "output";
    private static final String DEBUG = "debug";
    private static final String PARAMETER = "parameter";
    private boolean _debug = false;

    @Override
    protected List<Option> getCLIOptionsImpl() {


        List<Option> erg = new ArrayList<Option>();

        Option o;

        o = new Option(CONTROLLER,true,"Controller hostname");
        o.setRequired(true);
        erg.add(o);

        o = new Option(SSL,true,"Controller using ssl");
        o.setRequired(false);
        erg.add(o);

        o = new Option(PORT,true,"Controller Port (Defaults: 80,http  443,ssl)");
        o.setRequired(false);
        erg.add(o);



        o = new Option(USERNAME,true,"Controller username");
        o.setRequired(true);
        erg.add(o);

        o = new Option(PASSWORD,true,"Controller password");
        o.setRequired(true);
        erg.add(o);

        o = new Option(ACCOUNT,true,"Controller Account (Default: Customer1)");
        o.setRequired(false);
        erg.add(o);


        o = new Option(INFILE,true,"Jasperfile to execute");
        o.setRequired(true);
        erg.add(o);


        o = new Option(OUTFILE,true,"File to generate");
        o.setRequired(true);
        erg.add(o);


        o = new Option(DEBUG,false,"Jasperfile to execute");
        o.setRequired(false);
        erg.add(o);


        o = new Option(PARAMETER,true,"Parameter (KEY=Value) can add multiple times");
        o.setRequired(false);
        erg.add(o);


        return erg;
    }

    @Override
    protected int executeImpl(OptionWrapper optionWrapper) throws CommandException {
        _debug = optionWrapper.hasOption(DEBUG);

        ReportExecutionEnvironment ev = createExecutionEnvironment(optionWrapper.getOptionValue(CONTROLLER), optionWrapper.getOptionValue(USERNAME),
                optionWrapper.getOptionValue(PASSWORD), optionWrapper.getOptionValue(ACCOUNT, "Customer1"),
                optionWrapper.getOptionValue(PORT, optionWrapper.hasOption(SSL) ? "443" : "80"), optionWrapper.hasOption(SSL));


        String[] paras = optionWrapper.getOptionValues(PARAMETER);

        if (paras != null) {
            for (String p : paras) {
                String[] sp = p.split("=");
                if (sp.length != 2) {
                    if (_debug) {
                        System.out.println("Error parsing Argument :" + p);
                    }
                } else {
                    ev.setParameter(sp[0], sp[1]);
                    }

                }
            }



        try {
            String file =  optionWrapper.getOptionValue(INFILE);
            if (file.endsWith(".jasper")) {
                runReport(ev, file, optionWrapper.getOptionValue(OUTFILE));
            }
            if (file.endsWith(".jrxml")) {
                runSrcReport(ev, file, optionWrapper.getOptionValue(OUTFILE));
            }

        } catch (ReportExportException e) {
             if (_debug) e.printStackTrace();
            return 1;
        }

        return 0;

//        ReportExecutionEnvironment env = new ReportExecutionEnvironment(new ADDataAdapter("demo2.appdynamics.com", "demouser", "apm13ad3r", "80"));
//        env.executeCompiledReport("./src/test/resources/sample/Blank_A4.jasper",new PDFExporter("/tmp/report.pdf"));

    }

    private void runReport(ReportExecutionEnvironment ev, String infile, String outfile) throws ReportExportException {
        ev.executeCompiledReport(infile,new PDFExporter(outfile));
    }

    private void runSrcReport(ReportExecutionEnvironment ev, String infile, String outfile) throws ReportExportException {
        ev.executeSrcReport(infile,new PDFExporter(outfile));
    }


    private ReportExecutionEnvironment createExecutionEnvironment(String host, String user, String passwd, String account, String port, boolean useSSL) {
        return new ReportExecutionEnvironment(new ADDataAdapter(host,user,account,passwd,port,useSSL));
    }

    @Override
    public String getName() {
        return "runReport";
    }

    @Override
    public String getDescription() {
        return "Run a precompiled Jasper Report";
    }
}
