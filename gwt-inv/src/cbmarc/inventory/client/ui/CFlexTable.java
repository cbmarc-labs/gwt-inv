/**
 * 
 */
package cbmarc.inventory.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * @author MCOSTA
 *
 */
public class CFlexTable extends FlexTable {

	public CFlexTable() {
		super();
		
		getRowFormatter().addStyleName(0, "flexTableHeader");

		sinkEvents(Event.ONMOUSEOVER|Event.ONMOUSEOUT);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onBrowserEvent(com.google.gwt.user.client.Event)
	 */
	@Override
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);

		Element td = getEventTargetCell(event);
		if (td == null) return;
		
		Element tr = DOM.getParent(td);
		int row = DOM.getChildIndex(DOM.getParent(tr), tr);
		if(row == 0) return;
		
		switch (DOM.eventGetType(event)) {
			case Event.ONMOUSEOVER:
				DOM.setStyleAttribute(tr, "backgroundColor", "#ddd");
				break;
			case Event.ONMOUSEOUT:
				DOM.setStyleAttribute(tr, "backgroundColor","#eee");
				break;
		}
	}
	
}
