package de.appdynamics.ace.report.jasperreports.studio.adapter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRSingletonCache;

public class ADExecuterFactoryBundle implements JRQueryExecuterFactoryBundle{

	private static final String AMQL_LANGUAGE = "amql";
	private static JRQueryExecuterFactoryBundle _instance;
	private static final JRSingletonCache<QueryExecuterFactory> cache = new JRSingletonCache<QueryExecuterFactory>(QueryExecuterFactory.class);


	public static JRQueryExecuterFactoryBundle getInstance() {
		if (_instance  == null)
			_instance = new ADExecuterFactoryBundle();
		return _instance ;
	}

	private String[] languages = new String [] {AMQL_LANGUAGE };

	@Override
	public String[] getLanguages() {
		// TODO Auto-generated method stub
		return languages ;
	}

	@Override
	public QueryExecuterFactory getQueryExecuterFactory(String language)
			throws JRException {
		if (language.equals(AMQL_LANGUAGE)) {
			return cache.getCachedInstance(ADQueryExecuterFactory.class.getName()); 
		}
		return null;
	}
	
	

}
