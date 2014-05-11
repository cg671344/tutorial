package com.displaytag.myDisplaytag.tableDecrator;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.TableDecorator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.TableModel;

public class TableWitdhDecrator extends TableDecorator {
	@SuppressWarnings("unchecked")
	public void init(PageContext pageContext, Object decorated,
			TableModel tableModel) {
		List list = tableModel.getHeaderCellList();
		String style = null;
		Object thclass = null;
		for (Iterator it = list.iterator(); it.hasNext();) {
			HeaderCell hc = (HeaderCell) it.next();
			thclass = hc.getHeaderAttributes().get("class");
			Pattern p1 = Pattern.compile("(w\\d+%)");
			Pattern p2 = Pattern.compile("(w\\d+)");
			if (thclass != null) {
				Matcher m1 = p1.matcher(thclass.toString());
				if (m1.find()) {
					String findS = m1.group();
					style = (String) hc.getHeaderAttributes().get("style");
					String width = findS.substring(1, findS.length() - 1);
					hc.getHeaderAttributes().put("style",
							getStyle(style, "width:" + width + "%"));
				} else {
					Matcher m2 = p2.matcher(thclass.toString());
					if (m2.find()) {
						String findS = m2.group();
						style = (String) hc.getHeaderAttributes().get("style");
						String width = findS.substring(1, findS.length());
						hc.getHeaderAttributes().put("style",
								getStyle(style, "width:" + width + "px"));
					}
				}
			}
		}
		super.init(pageContext, decorated, tableModel);
	}

	private String getStyle(String oldStyle, String newStyle) {
		if ((oldStyle == null) || ("".equals(oldStyle))) {
			return newStyle;
		}
		return ";" + newStyle;
	}
}
