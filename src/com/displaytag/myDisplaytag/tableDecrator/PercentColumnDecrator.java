package com.displaytag.myDisplaytag.tableDecrator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class PercentColumnDecrator implements DisplaytagColumnDecorator {  
	public Object decorate(Object obj, PageContext pagecontext,
			MediaTypeEnum mediatypeenum) throws DecoratorException {
	   Double value = (Double) obj;
	   Double displayPecentValue = value * 100;
	   NumberFormat fmt = new DecimalFormat("0.0");
	   String str = fmt.format(displayPecentValue) + "%";
	   return str;
	}  
}  
