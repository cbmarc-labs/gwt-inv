/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * @author MCOSTA
 *
 */
public class DepartamentoView extends Composite 
		implements DepartamentoPresenter.Display {
	interface uiBinder extends UiBinder<Widget, DepartamentoView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	public DepartamentoView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
}
