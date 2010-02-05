/**
 * 
 */
package cbmarc.inventory.client.mvp.device;


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
public class DeviceView extends Composite implements DevicePresenter.Display {
	interface uiBinder extends UiBinder<Widget, DeviceView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel content;
	
	public DeviceView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}
}
