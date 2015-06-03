package de.appdynamics.ace.report.jasperreports.studio.adapter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.design.JRDefaultCompilationSourceCode;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;

import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;
import com.jaspersoft.studio.utils.parameter.SimpleValueParameter;

import de.appdynamics.ace.metric.query.data.DataMap;
import de.appdynamics.ace.metric.query.parser.CompiledRestMetricQuery;
import de.appdynamics.ace.metric.query.parser.MetricParserException;
import de.appdynamics.ace.metric.query.parser.MetricQuery;
import de.appdynamics.ace.metric.query.parser.QueryException;
import de.appdynamics.ace.metric.query.rest.ControllerRestAccess;
import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapterService;
import de.appdynamics.ace.report.jasperreports.amql.adapter.AMQLQueryExecuter;

public class AMQLFieldsProvider implements IFieldsProvider {

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		con.contributeParameters(parameters);
		ParameterUtil.setParameters(jConfig, jDataset, parameters);
		
		Map<String, JRValueParameter> tmpMap = ParameterUtil.convertMap(parameters, jDataset);
		tmpMap.put(JRParameter.REPORT_PARAMETERS_MAP, new SimpleValueParameter(new HashMap<String, JRValueParameter>()));

		AMQLQueryExecuter executor = new AMQLQueryExecuter(DefaultJasperReportsContext.getInstance(), 
				jDataset, tmpMap);

		return executor.retrieveFields(((ADDataAdapterService)con).getRestAdapter());
		
		
	
	}

}
