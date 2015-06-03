package de.appdynamics.ace.report.jasperreports.studio.adapter;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.swt.graphics.Image;

import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapter;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapter;
import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapterService;

public class ADDataAdapterFactory implements DataAdapterFactory {

	public ADDataAdapterFactory() {
		
	}

	@Override
	public DataAdapterDescriptor createDataAdapter() {
		// TODO Auto-generated method stub
		return new ADDataAdapterDescriptor();
	}



	
	
	
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Appdynamics Rest Data Adapter";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "This will provide a REST Metric Query data adapter to Query AD Controller Metrics via REST calls";
	}

	@Override
	public Image getIcon(int size) {
			if (size == 16) {
				return Activator.getDefault().getImage("icons/controller.png"); //$NON-NLS-1$
			}
			return null;
		
	}

	@Override
	public IDataAdapterCreator iReportConverter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDeprecated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDataAdapterClassName() {

		return ADDataAdapter.class.getName();


	}

	@Override
	public DataAdapterService createDataAdapterService(
			JasperReportsContext jasperReportsContext, DataAdapter dataAdapter) {

		// TODO Auto-generated method stub
		return new ADDataAdapterService(JasperReportsConfiguration.getDefaultJRConfig(),dataAdapter);

	}


}
