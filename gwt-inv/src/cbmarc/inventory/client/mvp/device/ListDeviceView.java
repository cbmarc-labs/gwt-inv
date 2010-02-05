/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ListDeviceView extends Composite 
		implements ListDevicePresenter.Display {
	interface uiBinder extends UiBinder<Widget, ListDeviceView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button addButton;
	@UiField Button deleteButton;
	
	@UiField FlexTable table;
	@UiField Label nodataLabel;
	@UiField Label listheaderLabel;
	
	public ListDeviceView() {
		initWidget(uiBinder.createAndBindUi(this));

		table.getColumnFormatter().setWidth(0, "15px");
	}
	
	/**
	 * @param e
	 */
	@UiHandler("addButton")
	void onAddButtonClick(ClickEvent e) {
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Device> data) {
		int size = data.size();
		
		table.removeAllRows();
		
		table.setWidget(0, 0, new CheckBox());
		table.setText(0, 1, "ID");
		table.setText(0, 2, "DATE");
		table.getRowFormatter().addStyleName(0, "flexTableHeader");

		if (data != null) {
			for (int i = 0; i < size; ++i) {
				table.setWidget(i+1, 0, new CheckBox());
				table.setText(i+1, 1, data.get(i).getId().toString());
				table.setText(i+1, 2, DateTimeFormat.
						getFormat("dd/MM/yyyy HH:mm:ss").
						format(data.get(i).getDate()));
			}
		}
		
		listheaderLabel.setText(size + " Items");
		nodataLabel.setVisible(size==0?true:false);
	}
	
	/**
	 * @return
	 */
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();
		
		for (int i = 0; i < table.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox)table.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}
		
		return selectedRows;
	}
	
	/**
	 * 
	 */
	private void selectAllRows() {
		boolean check = ((CheckBox)table.getWidget(0, 0)).getValue(); 
		
		for(int i = 1; i < table.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox)table.getWidget(i, 0);
			checkBox.setValue(check);
		}
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.device.ListDevicePresenter.Display#getClickedRow(com.google.gwt.event.dom.client.ClickEvent)
	 */
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = table.getCellForEvent(event);
	    
		if (cell != null) {
			// Suppress clicks if the user is actually selecting the check box
			if(cell.getRowIndex() == 0) selectAllRows();
			if(cell.getCellIndex() > 0) selectedRow = cell.getRowIndex();
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
	public HasClickHandlers getTable() {
		return table;
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ListContactPresenter.Display#asWidget()
	 */
	public Widget asWidget() {
		  return this;
	}
}
