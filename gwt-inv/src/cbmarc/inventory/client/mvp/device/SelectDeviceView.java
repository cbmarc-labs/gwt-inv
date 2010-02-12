/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.List;

import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class SelectDeviceView extends Composite 
		implements SelectDevicePresenter.Display {
	interface uiBinder extends UiBinder<Widget, SelectDeviceView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button cancelButton;
	
	@UiField FlexTable table;
	@UiField Label nodataLabel;
	@UiField Label listheaderLabel;
	
	public SelectDeviceView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * @param data
	 */
	@Override
	public void setData(List<Device> data) {
		int size = data.size();
		
		table.removeAllRows();
		
		table.setText(0, 0, "S/N");
		table.setText(0, 1, "Nombre");
		table.setText(0, 2, "Marca");
		table.setText(0, 3, "Modelo");
		table.setText(0, 4, "Tipo");
		table.getRowFormatter().addStyleName(0, "flexTableHeader");

		if (data != null) {
			for (int i = 0; i < size; ++i) {
				table.setText(i+1, 0, data.get(i).getSn());
				table.setText(i+1, 1, data.get(i).getNombre());
				table.setText(i+1, 2, data.get(i).getMarca());
				table.setText(i+1, 3, data.get(i).getModelo());
				table.setText(i+1, 4, data.get(i).getTipo());
			}
		}
		
		listheaderLabel.setText(size + " Items");
		nodataLabel.setVisible(size==0?true:false);
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.device.ListDevicePresenter.Display#getClickedRow(com.google.gwt.event.dom.client.ClickEvent)
	 */
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = table.getCellForEvent(event);
	    
		if (cell != null) {
			selectedRow = cell.getRowIndex();
		}
	    
		return selectedRow;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ListContactPresenter.Display#getDeleteButton()
	 */
	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.device.SelectDevicePresenter.Display#getTable()
	 */
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
