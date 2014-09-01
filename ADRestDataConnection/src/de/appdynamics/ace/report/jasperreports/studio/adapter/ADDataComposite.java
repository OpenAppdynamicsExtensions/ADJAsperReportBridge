package de.appdynamics.ace.report.jasperreports.studio.adapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JasperReportsContext;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.WSecretText;

public class ADDataComposite extends ADataAdapterComposite{

	private Spinner spinnerRecords;
	private Text textUsername;
	private WSecretText textPassword;
	private Text textAccount;
	private Button useSSL;
	private Text textHost;
	private Text textPort;

	public ADDataComposite(Composite parent, int style, JasperReportsContext jrContext) {
		super(parent,style,jrContext);
		setLayout(new GridLayout(2, false));
				Label lblNewLabel = new Label(this, SWT.NONE);
				lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				lblNewLabel.setText("username");
				textUsername = new Text(this, SWT.BORDER);
				textUsername.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

				lblNewLabel = new Label(this, SWT.NONE);
				lblNewLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
				lblNewLabel.setText("password");

				textPassword = new WSecretText(this, SWT.BORDER | SWT.PASSWORD);
				textPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
				lblNewLabel = new Label(this, SWT.NONE);
				lblNewLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
				lblNewLabel.setText("Account");

				textAccount = new Text(this, SWT.BORDER);
				textAccount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				lblNewLabel = new Label(this, SWT.NONE);
				lblNewLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
				lblNewLabel.setText("Hostname");

				textHost = new Text(this, SWT.BORDER);
				textHost.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				lblNewLabel = new Label(this, SWT.NONE);
				lblNewLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
				lblNewLabel.setText("Port");

				textPort = new Text(this, SWT.BORDER);
				textPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
				lblNewLabel = new Label(this, SWT.NONE);
				lblNewLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
				lblNewLabel.setText("use SSL");

				useSSL = new  Button(this, SWT.CHECK);
				useSSL.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
				
		
	}
	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(SWTObservables.observeText(textUsername,SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "username")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(textHost,SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "host")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(textPort,SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "port")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(textPassword,SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "password")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(textAccount,SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "account")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(useSSL),
				PojoObservables.observeValue(dataAdapter, "useSSL")); //$NON-NLS-1$
	}

	@Override
	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new ADDataAdapterDescriptor();
		// config Adapter
		
		ADDataAdapter ad = (ADDataAdapter) dataAdapterDesc.getDataAdapter();
		ad.setAccount(textAccount.getText());
		ad.setUsername(textUsername.getText());
		ad.setPassword(textPassword.getText());
		ad.setHost(textHost.getText());
		ad.setPort(textPort.getText());
		ad.setUseSSL(useSSL.getSelection());
		
		return dataAdapterDesc;
	}
	

}
