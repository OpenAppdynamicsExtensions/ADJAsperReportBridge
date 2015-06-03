package de.appdynamics.ace.report.jasperreports.studio;

import java.util.ArrayList;
import java.util.List;

import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapterServiceFactory;
import de.appdynamics.ace.report.jasperreports.amql.adapter.ADExecuterFactoryBundle;
import de.appdynamics.ace.report.jasperreports.amql.functions.ADFunctionBundle;
import net.sf.jasperreports.data.DataAdapterServiceFactory;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;
import net.sf.jasperreports.functions.FunctionsBundle;

public class ADExtensionRegisterFactory implements ExtensionsRegistryFactory {

	public ADExtensionRegisterFactory() {
		
	}
	@Override
	public ExtensionsRegistry createRegistry(String registryId,
			JRPropertiesMap properties) {
		// TODO Auto-generated method stub
		return extensionsRegistry;
	}
	
	private static final ExtensionsRegistry extensionsRegistry = new ExtensionsRegistry() {
		
		private ArrayList<JRQueryExecuterFactoryBundle> queryExecuterFactories;
		private ArrayList<DataAdapterServiceFactory> dataAdapterServiceFactories;
		private ArrayList<FunctionsBundle> functionBundles;
	

		@SuppressWarnings("unchecked")
		public <T> List<T> getExtensions(Class<T> extensionType) {
			if (DataAdapterServiceFactory.class.equals(extensionType)) {
				if (dataAdapterServiceFactories == null) {
					dataAdapterServiceFactories = new ArrayList<DataAdapterServiceFactory>();
					ADDataAdapterServiceFactory obj = ADDataAdapterServiceFactory.getInstance();
					dataAdapterServiceFactories.add(obj);
				}
				return (List<T>) dataAdapterServiceFactories;
			} else if (JRQueryExecuterFactoryBundle.class.equals(extensionType)) {
				if (queryExecuterFactories == null) {
					queryExecuterFactories = new ArrayList<JRQueryExecuterFactoryBundle>();
					queryExecuterFactories.add(ADExecuterFactoryBundle.getInstance());
				}
				return (List<T>) queryExecuterFactories;
			} else if (FunctionsBundle.class.equals(extensionType)) {
				if (functionBundles == null) {
					functionBundles = new ArrayList<FunctionsBundle>();
					functionBundles.add(ADFunctionBundle.getInstance());
				}
			}
			return null;
		}
	};

}
