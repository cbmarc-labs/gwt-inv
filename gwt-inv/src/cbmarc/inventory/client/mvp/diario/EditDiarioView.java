/**
 * 
 */
package cbmarc.inventory.client.mvp.diario;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * @author MCOSTA
 *
 */
public class EditDiarioView extends Composite 
		implements EditDiarioPresenter.Display {
	interface uiBinder extends UiBinder<Widget, EditDiarioView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField HasClickHandlers listButton;
	
	@UiField DateBox fecha;
	@UiField ListBox hora;
	@UiField ListBox minuto;
	@UiField TextArea accion;
	
	@UiField HasClickHandlers submitButton;
	@UiField HasClickHandlers cancelButton;
	
	public EditDiarioView() {
		// Problem with Enter on TextArea
		//sinkEvents(Event.ONKEYDOWN);
		initWidget(uiBinder.createAndBindUi(this));
		
		this.fecha.setFormat(new DateBox.DefaultFormat(
				DateTimeFormat.getFormat("dd / MM / yyyy")));
		
		for(int i = 0; i < 24; i ++) hora.addItem(String.valueOf(i));
		for(int i = 0; i < 60; i ++) minuto.addItem(String.valueOf(i));
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#onBrowserEvent(com.google.gwt.user.client.Event)
	 */
	@Override
	public void onBrowserEvent(Event event) {
		if(event.getKeyCode() == KeyCodes.KEY_ENTER) {
			this.submitButton.fireEvent(new ClickEvent(){});
			//this.submitButton.click();
		} else if(event.getKeyCode() == KeyCodes.KEY_ESCAPE) {
			this.cancelButton.fireEvent(new ClickEvent(){});
		}
		
		super.onBrowserEvent(event);
	}

	@UiHandler("listButton")
	void onCancelButtonClick(ClickEvent e) {
		//Window.alert("Hello!");
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasClickHandlers getListButton() {
		return listButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return submitButton;
	}

	@Override
	public void reset() {
		// TODO this.fecha.setText("");
		this.setHora(new Date());
		this.accion.setValue("");
	}

	@Override
	public TextArea getAccion() {
		return this.accion;
	}

	@Override
	public DateBox getFecha() {
		return this.fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha.setValue(fecha);
	}

	@Override
	public Date getHora() {
		String h = this.hora.getItemText(this.hora.getSelectedIndex()) + ":" + 
			this.minuto.getItemText(this.minuto.getSelectedIndex());
		
		return DateTimeFormat.getFormat("H:mm").parse(h);
	}

	@Override
	public void setHora(Date date) {
		this.hora.setItemSelected(Integer.parseInt(
				DateTimeFormat.getFormat("H").format(date)), true);
		this.minuto.setItemSelected(Integer.parseInt(
				DateTimeFormat.getFormat("mm").format(date)), true);
	}
}
