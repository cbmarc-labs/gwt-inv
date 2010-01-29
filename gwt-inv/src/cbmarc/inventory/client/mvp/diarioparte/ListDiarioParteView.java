/**
 * 
 */
package cbmarc.inventory.client.mvp.diarioparte;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.DiarioParte;

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
public class ListDiarioParteView extends Composite 
		implements ListDiarioPartePresenter.Display {
	interface uiBinder extends UiBinder<Widget, ListDiarioParteView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button addButton;
	@UiField Button deleteButton;
	
	@UiField FlexTable table;
	
	public ListDiarioParteView() {
		initWidget(uiBinder.createAndBindUi(this));

		table.getColumnFormatter().setWidth(0, "15px");
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
	public void setData(List<DiarioParte> data) {
		table.removeAllRows();

		if (data != null) {
			for (int i = 0; i < data.size(); ++i) {
				table.setWidget(i, 0, new CheckBox());
				//table.setText(i, 1, data.get(i).getFecha().toString());
				//table.setText(i, 2, data.get(i).getHora().toString());
				table.setText(i, 3, data.get(i).getAccion());
			}
		}
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
	
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = table.getCellForEvent(event);
	    
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
