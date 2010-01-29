/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;


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
public class ParteView extends Composite implements PartePresenter.Display {
	interface uiBinder extends UiBinder<Widget, ParteView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel contentPanel;
	
	public ParteView() {
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
