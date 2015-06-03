package de.appdynamics.ace.report.jasperreports.studio.adapter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import de.appdynamics.ace.report.jasperreports.amql.adapter.ADDataAdapter;

public class ADDataAdapterDescriptor extends DataAdapterDescriptor implements IFieldsProvider{

	private ADDataAdapter _adapter;

	@Override
	public DataAdapter getDataAdapter() {
		if (_adapter == null) _adapter =  new ADDataAdapter();
		return _adapter;
	}
	
	public void setDataAdapter(DataAdapter dataAdapter) {
		_adapter = (ADDataAdapter) dataAdapter;
	};

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {
		
		return getFieldsProvider().getFields(con, jConfig, jDataset);
		
		
	}
	
	private IFieldsProvider getFieldsProvider() {
		return new AMQLFieldsProvider();
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataAdapterEditor getEditor() {
		// TODO Auto-generated method stub
		return new ADDataEditor();
	}
	@Override
	public Image getIcon(int size) {
		
			if (size == 16)
				return Activator.getDefault().getImage("icons/controller.png"); //$NON-NLS-1$ 
			return null;
		
	}

}
