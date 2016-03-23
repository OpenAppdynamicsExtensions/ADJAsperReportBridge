package de.appdynamics.jasperreport.cli;

import com.appdynamics.ace.util.cli.api.api.CommandlineExecution;

/**
 * Created by stefan.marx on 10.09.15.
 */
public class Main {
    public static void main(String[] args) {


        try {

            CommandlineExecution cle = new CommandlineExecution("Sandbox");
            cle.setHelpVerboseEnabled(false);

            cle.addCommand(new ReportCommand());


            cle.execute(args);
        } finally {

        }
    }
}

