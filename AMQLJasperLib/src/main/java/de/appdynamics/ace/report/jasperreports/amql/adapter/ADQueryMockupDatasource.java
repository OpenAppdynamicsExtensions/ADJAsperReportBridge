package de.appdynamics.ace.report.jasperreports.amql.adapter;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ADQueryMockupDatasource implements JRDataSource {

	int i = 0;
	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return (i++ < 20);
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		// TODO Auto-generated method stub
		return jrField.getName()+" __ Mockup";
	}

}
