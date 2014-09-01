package de.appdynamics.ace.report.jasperreports.studio.adapter;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceFactory;
import net.sf.jasperreports.engine.JasperReportsContext;

public class ADDataAdapterServiceFactory implements DataAdapterServiceFactory {

	private static ADDataAdapterServiceFactory _instance;

	public static ADDataAdapterServiceFactory getInstance() {
		if (_instance == null)
				_instance = new ADDataAdapterServiceFactory();

			return _instance;
	}
	
	@Override
	public DataAdapterService getDataAdapterService(
			JasperReportsContext jasperReportsContext, DataAdapter dataAdapter) {
		// TODO Auto-generated method stub
		if (dataAdapter instanceof ADDataAdapter) return new ADDataAdapterService(jasperReportsContext,dataAdapter);
		else return null;
	}

}
