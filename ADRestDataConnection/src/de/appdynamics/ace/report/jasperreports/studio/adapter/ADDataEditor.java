package de.appdynamics.ace.report.jasperreports.studio.adapter;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class ADDataEditor implements DataAdapterEditor {

	ADDataComposite composite = null;
	
	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		// TODO Auto-generated method stub
		this.composite.setDataAdapter(dataAdapter);
	}

	@Override
	public DataAdapterDescriptor getDataAdapter() {
		// TODO Auto-generated method stub
		return this.composite.getDataAdapter();
	}

	@Override
	public ADataAdapterComposite getComposite(Composite parent, int style,
			WizardPage wizardPage, JasperReportsContext jrContext) {
		if (composite == null)
			composite = new ADDataComposite(parent, style, jrContext);
		return composite;
	}

	@Override
	public String getHelpContextId() {
		// TODO Auto-generated method stub
		return composite.getHelpContextId();
	}

}
