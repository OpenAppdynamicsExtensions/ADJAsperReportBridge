package de.appdynamics.ace.report.jasperreports.studio.adapter;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;

import de.appdynamics.ace.metric.query.data.Column;
import de.appdynamics.ace.metric.query.data.DataMap;
import de.appdynamics.ace.metric.query.data.DataRow;
import de.appdynamics.ace.metric.query.data.TimestampColumn;
import de.appdynamics.ace.metric.query.data.TimestampDataObject;
import de.appdynamics.ace.metric.query.data.ValueColumn;
import de.appdynamics.ace.metric.query.data.ValueDataObject;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ADRestDataSource implements JRDataSource {

	private DataMap _result;
	private HashMap<String, Column> _map;
	private Iterator<DataRow> _it;
	private DataRow _current;

	public ADRestDataSource(DataMap results) {
		_result = results;
		_map = new HashMap<String, Column>();
		for (Column c : _result.getHeader()) {
			_map.put(c.getName(), c);
		}
		_it = _result.getOrderedRows().iterator();
		
	}

	@Override
	public boolean next() throws JRException {
		if (_it.hasNext()) {
			_current = _it.next();
			return true;
		}
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		try {
			// TODO Auto-generated method stub
			Column c;
			if (!_map.containsKey(jrField.getName())) throw new JRException("Field not foun din result ("+jrField.getName()+")");
			c = _map.get(jrField.getName());
			String valueClass = jrField.getValueClassName();
			
			
			if (valueClass.equals(String.class.getName())) {
				return _current.findData(c).getTextValue();
				
			}
			if (valueClass.equals(Double.class.getName())) {
				if (!(c instanceof ValueColumn)) throw new JRException("Incompatible Type Definition on Field "+jrField.getName());
				else return Double.valueOf(((ValueDataObject)_current.findData(c)).getValue());
				
			}
			if (valueClass.equals(Timestamp.class.getName())) {
				if (!(c instanceof TimestampColumn)) throw new JRException("Incompatible Type Definition on Field "+jrField.getName());
				else return new Timestamp(((TimestampDataObject)_current.findData(c)).getTimestampValue().getTime());
				
			}
			
			
			return null;
		} catch (NullPointerException e) {
			// Quick Fix, Some Metrics may not be available on all Timeslots or Paths ....
			return null;
		}
		
		
		
	}

}
