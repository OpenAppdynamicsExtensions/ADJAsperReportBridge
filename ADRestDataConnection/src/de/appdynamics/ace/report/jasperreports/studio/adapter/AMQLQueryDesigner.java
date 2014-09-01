package de.appdynamics.ace.report.jasperreports.studio.adapter;

import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.data.designer.AQueryDesignerContainer;
import com.jaspersoft.studio.data.designer.QueryDesigner;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class AMQLQueryDesigner extends QueryDesigner {
	protected StyledText queryTextArea;
	
	public Control createControl(Composite parent) {
		control = (StyledText) super.createControl(parent);
		
		return control;
	}

	protected void queryTextAreaModified() {
		// keep the query info updated
		((JRDesignQuery) jDataset.getQuery()).setText(queryTextArea.getText());
	}
}
