package de.appdynamics.ace.report.jasperreports.studio.adapter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.appdynamics.ace.metric.query.data.Column;
import de.appdynamics.ace.metric.query.data.DataMap;
import de.appdynamics.ace.metric.query.data.TextColumn;
import de.appdynamics.ace.metric.query.data.TimestampColumn;
import de.appdynamics.ace.metric.query.data.ValueColumn;
import de.appdynamics.ace.metric.query.parser.CompiledRestMetricQuery;
import de.appdynamics.ace.metric.query.parser.MetricParserException;
import de.appdynamics.ace.metric.query.parser.MetricQuery;
import de.appdynamics.ace.metric.query.parser.QueryException;
import de.appdynamics.ace.metric.query.rest.ControllerRestAccess;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.JRAbstractQueryExecuter;
import net.sf.jasperreports.engine.query.JRQueryExecuter;

public class AMQLQueryExecuter extends JRAbstractQueryExecuter {

	private Map<String, ? extends JRValueParameter> _parameters;
	private JRDataset _dataset;
	private JasperReportsContext _context;
	private ControllerRestAccess _restConnection;

	public AMQLQueryExecuter(JasperReportsContext jasperReportsContext,
			JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters) {
		super(jasperReportsContext,dataset,parameters);
		
		_context = jasperReportsContext;
		_dataset = dataset;
		_parameters = parameters;
		
		_restConnection = (ControllerRestAccess) getParameterValue(ADAdapterConstants.CONNECTION,true);
		parseQuery();
	}

	@Override
	public JRDataSource createDatasource() throws JRException {
		if (_restConnection == null) throw new JRException("Need valid Connection");
		
		try {
			CompiledRestMetricQuery exec = compileStatement();
			DataMap results = exec.execute(_restConnection);
			return new ADRestDataSource(results);
		} catch (MetricParserException e) {
			throw new JRException("Error while Parsing Query :"+getQueryString(),e);
		} catch (QueryException e) {
			throw new JRException("Error while Parsing Query :"+getQueryString(),e);
		}
		
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean cancelQuery() throws JRException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<JRDesignField> retrieveFields(ControllerRestAccess conn) throws JRException {

		ArrayList<JRDesignField> erg = new ArrayList<JRDesignField>();
		
		try {
			
		
			CompiledRestMetricQuery exec = compileStatement();
			
			exec.setAggregated(true);
			DataMap results = exec.execute(conn);
			System.out.println("N:"+results);
			
			for (Column c : results.getHeader()) {
				if (c instanceof ValueColumn) {
					erg.add(new ADField(c.getName(),Double.class));
				} else if (c instanceof TimestampColumn) {
					erg.add(new ADField(c.getName(),Timestamp.class));
				} else {
					erg.add(new ADField(c.getName()));
				}
				
				
			}
			
			
		} catch (MetricParserException e) {
			// TODO Auto-generated catch block
			throw new JRException("Parse not successfull : "+this.getQueryString(), e);
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			throw new JRException("Query not sucessfull : "+this.getQueryString(), e);
		} finally {}
		
		return erg;
	
	}




	private CompiledRestMetricQuery compileStatement()
			throws MetricParserException {
		MetricQuery mq = new MetricQuery();
		
		String queryString = getQueryString();
		ParameterWeaver weaver = new ParameterWeaver(queryString);
		visitQueryParameters(weaver );
		
		CompiledRestMetricQuery exec = mq.parse(weaver.getQueryString());
		
		return exec;
	}

	@Override
	protected String getParameterReplacement(String parameterName) {
		// TODO Auto-generated method stub
		return "##"+parameterName+"##";
	}

	private class ParameterWeaver implements QueryParameterVisitor{

		private String _qs;

		public ParameterWeaver(String queryString) {
			_qs = queryString;
		}

		public String getQueryString() {
			
			return _qs;
		}

		@Override
		public void visit(QueryParameter parameter)
				throws VisitExceptionWrapper {
			
			JRValueParameter pval = getValueParameter(parameter.getName());
			String value = ""+pval.getValue();
			
			_qs = _qs.replaceFirst(getParameterReplacement(parameter.getName()),
					value);
			
		}

		@Override
		public void visit(ValuedQueryParameter valuedQueryParameter)
				throws VisitExceptionWrapper {
			return;
		}
		
	}
}
