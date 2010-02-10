/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditDepartamentoView extends Composite 
		implements EditDepartamentoPresenter.Display {
	interface uiBinder extends UiBinder<Widget, EditDepartamentoView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField HasClickHandlers listButton;
	
	@UiField TextBox nombre;
	@UiField TextArea observaciones;
	
	@UiField HasClickHandlers submitButton;
	@UiField HasClickHandlers cancelButton;
	
	public EditDepartamentoView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return this.cancelButton;
	}

	/**
	 * @return the nombre
	 */
	public final TextBox getNombre() {
		return nombre;
	}

	/**
	 * @return the observaciones
	 */
	public final TextArea getObservaciones() {
		return observaciones;
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
		this.nombre.setValue("");
		this.observaciones.setValue("");
	}
}
