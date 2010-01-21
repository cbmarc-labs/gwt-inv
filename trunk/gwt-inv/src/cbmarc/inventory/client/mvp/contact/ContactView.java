/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ContactView extends Composite 
		implements ContactPresenter.Display {
	interface uiBinder extends UiBinder<Widget, ContactView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel contentPanel;
	
	public ContactView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasWidgets getContent() {
		return contentPanel;
	}
}
