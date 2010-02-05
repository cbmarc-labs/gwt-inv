/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * @author MCOSTA
 *
 */
public class EditDeviceView extends Composite 
		implements EditDevicePresenter.Display {
	interface uiBinder extends UiBinder<Widget, EditDeviceView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField HasClickHandlers listButton;
	
	@UiField TextBox id;
	@UiField DateBox date;
	
	@UiField HasClickHandlers submitButton;
	@UiField HasClickHandlers cancelButton;
	
	public EditDeviceView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.id.setEnabled(false);
		this.date.setEnabled(false);
		this.date.setFormat(new DateBox.DefaultFormat(
				DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss")));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return this.cancelButton;
	}

	@Override
	public HasValue<Date> getDate() {
		return this.date;
	}

	@Override
	public HasValue<String> getId() {
		return this.id;
	}

	@Override
	public HasClickHandlers getListButton() {
		return this.listButton;
	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return this.submitButton;
	}

	@Override
	public void reset() {
		this.id.setValue("");
		this.date.setValue(null);
	}
}
