package de.appdynamics.ace.report.jasperreports.studio.functions;

import java.util.ArrayList;

import net.sf.jasperreports.functions.FunctionsBundle;

public class ADFunctionBundle extends  FunctionsBundle{
	private static ADFunctionBundle _instance;

	public static ADFunctionBundle getInstance() {
		if (_instance == null) _instance = new ADFunctionBundle();
		return _instance;
	}
	
	public static ArrayList<String> _functions = new ArrayList<String>();
	static {
		_functions.add("de.appdynamics.ace.report.jasperreports.studio.functions.PathFunctions");
	}
	public ADFunctionBundle() {
		super(_functions);
	}

}
