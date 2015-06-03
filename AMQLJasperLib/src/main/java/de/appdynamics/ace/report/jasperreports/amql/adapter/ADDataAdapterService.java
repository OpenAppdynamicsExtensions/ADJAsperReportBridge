package de.appdynamics.ace.report.jasperreports.amql.adapter;

import java.util.Map;

import de.appdynamics.ace.metric.query.rest.ControllerRestAccess;
import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;

public class ADDataAdapterService extends AbstractDataAdapterService {

	private ADDataAdapter _adapter;

	
	public ADDataAdapterService(JasperReportsContext jContext,DataAdapter dataAdapter) {
		super(jContext,dataAdapter);
		// TODO Auto-generated constructor stub
		_adapter = (ADDataAdapter) dataAdapter;
	}

	@Override
	public void contributeParameters(Map<String, Object> parameters)
			throws JRException {
		ControllerRestAccess connection = new ControllerRestAccess(_adapter.getHost(),
					_adapter.getPort(),_adapter.isUseSSL(),
					_adapter.getUsername(), _adapter.getPassword(), _adapter.getAccount());
		parameters.put(ADAdapterConstants.CONNECTION,connection);
		
	}

	public ControllerRestAccess getRestAdapter() {
		
		ControllerRestAccess connection = new ControllerRestAccess(_adapter.getHost(),
				_adapter.getPort(),_adapter.isUseSSL(),
				_adapter.getUsername(), _adapter.getPassword(), _adapter.getAccount());

		return connection;
	}

}
