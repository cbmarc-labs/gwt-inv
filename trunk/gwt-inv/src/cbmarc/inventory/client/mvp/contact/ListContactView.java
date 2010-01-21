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
	
	public ListContactView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("addButton")
	void onAddButtonClick(ClickEvent e) {
		//Window.alert("Hello!");
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}
}
