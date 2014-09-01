package de.appdynamics.ace.report.jasperreports.studio.adapter;

import de.appdynamics.ace.metric.query.rest.ControllerRestAccess;
import net.sf.jasperreports.data.AbstractDataAdapter;
import net.sf.jasperreports.data.DataAdapter;

public class ADDataAdapter extends  AbstractDataAdapter {

	
	private String host,port,username,password,account;
	boolean useSSL;
	private String _name;

	public  ADDataAdapter() {
		account = "customer1";
		useSSL = false;
		port = "8080";
	}
	@Override
	public String getName() {
		return _name;
	}
	@Override
	public void setName(String name) {
		_name = name;
		super.setName(name);
	}
	

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isUseSSL() {
		return useSSL;
	}

	public void setUseSSL(boolean b) {
		this.useSSL = b;
	}
	

}
