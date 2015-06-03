package de.appdynamics.ace.report.jasperreports.amql.functions;

import net.sf.jasperreports.functions.annotations.Function;
import net.sf.jasperreports.functions.annotations.FunctionCategories;
import net.sf.jasperreports.functions.annotations.FunctionParameter;
import net.sf.jasperreports.functions.annotations.FunctionParameters;

@FunctionCategories ({PathCategory.class})
public final class PathFunctions {
	// ===================== DAY function ===================== //
	  /**
	   * 
	   * Returns the day of a given date. Date object can be a String, long value (milliseconds) or Date instance itself.
	   */
	  @Function("PATH_SEGMENT")
	  @FunctionParameters({
		  @FunctionParameter("string"),
		  @FunctionParameter("index")
		    })
	  public static String PathSegment(String path,Integer index){
		 String[] pathSegments = path.split("\\|");
		 if (index >= pathSegments.length) index = pathSegments.length-1;
		 
		 if (index < 0) {
			 index = pathSegments.length-(index*-1);
		 }
		 
		 if (index<0) index=0;
				 
		 
	    return pathSegments[index];
	  }
}
