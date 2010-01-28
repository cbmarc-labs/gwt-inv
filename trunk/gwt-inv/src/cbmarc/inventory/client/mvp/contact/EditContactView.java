/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditContactView extends Composite 
		implements EditContactPresenter.Display {
	interface uiBinder extends UiBinder<Widget, EditContactView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Button listButton;
	
	@UiField TextBox firstName;
	@UiField TextBox lastName;
	@UiField TextBox emailAddress;
	
	@UiField Button submitButton;
	@UiField Button cancelButton;
	@UiField TabPanel tabs;
	
	public EditContactView() {
		sinkEvents(Event.ONKEYDOWN);
		initWidget(uiBinder.createAndBindUi(this));
		
		tabs.selectTab(0);
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#onBrowserEvent(com.google.gwt.user.client.Event)
	 */
	@Override
	public void onBrowserEvent(Event event) {
		if(event.getKeyCode() == KeyCodes.KEY_ENTER) {
			this.submitButton.click();
		} else if(event.getKeyCode() == KeyCodes.KEY_ESCAPE) {
			this.cancelButton.click();
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
		this.firstName.setText("");
		this.lastName.setText("");
		this.emailAddress.setText("");
	}

	@Override
	public HasValue<String> getEmailAddress() {
		return this.emailAddress;
	}

	@Override
	public HasValue<String> getFirstName() {
		return this.firstName;
	}

	@Override
	public HasValue<String> getLastName() {
		return this.lastName;
	}

	@Override
	public Focusable getFirstNameFocus() {
		return this.firstName;
	}
}
