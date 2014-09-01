package de.appdynamics.ace.report.jasperreports.studio.adapter;

import org.w3c.dom.Document;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;

public class ADOldConverter implements IDataAdapterCreator {

	@Override
	public DataAdapterDescriptor buildFromXML(Document docXML) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		return "com.jaspersoft.ireport.designer.connection.ADConnection"; //$NON-NLS-1$
	}
	

}
