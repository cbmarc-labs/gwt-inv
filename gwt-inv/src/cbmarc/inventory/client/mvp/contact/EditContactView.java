/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
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
	@UiField TabPanel tabs;
	
	public EditContactView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		tabs.selectTab(0);
	}
	
	@UiHandler("listButton")
	void onCancelButtonClick(ClickEvent e) {
		//Window.alert("Hello!");
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasClickHandlers getTestButton() {
		return listButton;
	}
}
