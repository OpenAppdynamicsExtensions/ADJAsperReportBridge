package de.appdynamics.ace.report.jasperreports.amql.adapter;

import net.sf.jasperreports.engine.design.JRDesignField;

public class ADField extends JRDesignField {

	public ADField(String name) {
		this(name,String.class);
	}

	public ADField(String name, Class cl) {
		setName(name);
		setValueClass(cl);
		setValueClassName(cl.getName());
	}
	
	

}
