/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditParteView extends Composite 
		implements EditPartePresenter.Display {
	interface uiBinder extends UiBinder<Widget, EditParteView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField HasClickHandlers listButton;
	
	@UiField TextBox atu;
	@UiField TextBox fecha;
	
	@UiField TextBox dis_id;
	
	@UiField HasClickHandlers selectDispositivoButton;
	
	@UiField DisclosurePanel diarioDisclosurePanel;
	@UiField Panel dispositivoFields;
	@UiField Panel dispositivo;
	@UiField Panel diario;
	
	@UiField HasClickHandlers submitButton;
	@UiField HasClickHandlers cancelButton;
	
	public EditParteView() {
		sinkEvents(Event.ONKEYDOWN);
		initWidget(uiBinder.createAndBindUi(this));
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
		this.atu.setText("");
		this.fecha.setValue("");
	}

	@Override
	public TextBox getAtu() {
		return this.atu;
	}

	@Override
	public TextBox getFecha() {
		return this.fecha;
	}

	@Override
	public HasWidgets getDiario() {
		return this.diario;
	}

	/**
	 * @return the dispositivo
	 */
	public final Panel getDispositivo() {
		return dispositivo;
	}

	/**
	 * @return the selectDispositivoButton
	 */
	public final HasClickHandlers getSelectDispositivoButton() {
		return selectDispositivoButton;
	}

	/**
	 * @return the dis_id
	 */
	public final HasValue<String> getDis_id() {
		return dis_id;
	}

	/**
	 * @return the dispositivoFields
	 */
	public final Panel getDispositivoFields() {
		return dispositivoFields;
	}

	/**
	 * @return the diarioDisclosurePanel
	 */
	public final DisclosurePanel getDiarioDisclosurePanel() {
		return diarioDisclosurePanel;
	}
}
