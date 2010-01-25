/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ListContactView extends Composite 
		implements ListContactPresenter.Display {
	interface uiBinder extends UiBinder<Widget, ListContactView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button addButton;
	@UiField Button deleteButton;
	@UiField FlexTable contactsTable;
	
	public ListContactView() {
		initWidget(uiBinder.createAndBindUi(this));

		contactsTable.getColumnFormatter().setWidth(0, "15px");
	}
	
	/**
	 * @param e
	 */
	@UiHandler("addButton")
	void onAddButtonClick(ClickEvent e) {
		//Window.alert("Hello!");
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Contact> data) {
		contactsTable.removeAllRows();

		if (data != null)
			for (int i = 0; i < data.size(); ++i) {
				contactsTable.setWidget(i, 0, new CheckBox());
				contactsTable.setText(i, 1, data.get(i).getFirstName());
				contactsTable.setText(i, 2, data.get(i).getLastName());
				contactsTable.setText(i, 3, data.get(i).getEmailAddress());
			}
	}
	
	/**
	 * @return
	 */
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();
		
		for (int i = 0; i < contactsTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox)contactsTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}
		
		return selectedRows;
	}
	
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = contactsTable.getCellForEvent(event);
	    
		if (cell != null) {
			// Suppress clicks if the user is actually selecting the check box

			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}
	    
		return selectedRow;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ListContactPresenter.Display#getAddButton()
	 */
	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ListContactPresenter.Display#getDeleteButton()
	 */
	@Override
	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	@Override
	public HasClickHandlers getcontactsTable() {
		return contactsTable;
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ListContactPresenter.Display#asWidget()
	 */
	public Widget asWidget() {
		  return this;
	}
}
