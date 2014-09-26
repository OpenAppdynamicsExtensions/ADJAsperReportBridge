package de.appdynamics.ace.report.jasperreports.studio.adapter;

import java.security.KeyStore.Entry;
import java.util.Map;
import java.util.Set;

import de.appdynamics.ace.metric.query.rest.ControllerRestAccess;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.JRQueryExecuter;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;

public class ADQueryExecuterFactory implements QueryExecuterFactory{

	
	private final static Object[] AD_BUILT_IN_PARAMETERS = {
		ADAdapterConstants.CONNECTION, ControllerRestAccess.class };
	private String _para;
	
	@Override
	public JRQueryExecuter createQueryExecuter(JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters)
			throws JRException {
		
		
		return new AMQLQueryExecuter(DefaultJasperReportsContext.getInstance(),dataset,parameters);
	}



	@Override
	public Object[] getBuiltinParameters() {
		return AD_BUILT_IN_PARAMETERS;
	}

	@Override
	public JRQueryExecuter createQueryExecuter(
			JasperReportsContext jasperReportsContext, JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters)
			throws JRException {
		 return new AMQLQueryExecuter(jasperReportsContext,dataset,parameters);
		 
	}

	@Override
	public boolean supportsQueryParameterType(String className) {
		// TODO Auto-generated method stub
		return true;
	}

}
