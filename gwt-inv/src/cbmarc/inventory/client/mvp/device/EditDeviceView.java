/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * @author MCOSTA
 *
 */
public class EditDeviceView extends Composite 
		implements EditDevicePresenter.Display {
	interface uiBinder extends UiBinder<Widget, EditDeviceView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField HasClickHandlers listButton;
	
	@UiField TextBox sn;
	@UiField HasValue<String> nombre;
	@UiField HasValue<String> marca;
	@UiField HasValue<String> modelo;
	@UiField HasValue<String> tipo;
	@UiField HasValue<String> centro;
	@UiField HasValue<String> sociedad;
	@UiField TextBox departamento;
	@UiField Panel table;
	@UiField HasValue<String> ubicacion;
	@UiField DateBox fechaCompra;
	@UiField DateBox fechaFinGarantia;
	@UiField HasValue<String> proveedor;
	@UiField RadioButton mantenimiento_0;
	@UiField RadioButton mantenimiento_1;
	@UiField HasValue<String> observaciones;
	
	@UiField HasClickHandlers submitButton;
	@UiField HasClickHandlers cancelButton;
	
	public EditDeviceView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.fechaCompra.setFormat(new DateBox.DefaultFormat(
				DateTimeFormat.getFormat("dd/MM/yyyy")));
		this.fechaFinGarantia.setFormat(new DateBox.DefaultFormat(
				DateTimeFormat.getFormat("dd/MM/yyyy")));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return this.cancelButton;
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
		this.sn.setValue("");
		this.nombre.setValue("");
		this.marca.setValue("");
		this.modelo.setValue("");
		this.tipo.setValue("");
		this.centro.setValue("");
		this.sociedad.setValue("");
		this.departamento.setValue("Seleccionar");
		this.table.setVisible(false);
		this.ubicacion.setValue("");
		this.fechaCompra.setValue(new Date());
		this.fechaFinGarantia.setValue(new Date());
		this.proveedor.setValue("");
		this.mantenimiento_0.setValue(true);
		this.observaciones.setValue("");
	}

	@Override
	public TextBox getSn() {
		return this.sn;
	}

	/**
	 * @return the nombre
	 */
	public final HasValue<String> getNombre() {
		return nombre;
	}

	/**
	 * @return the marca
	 */
	public final HasValue<String> getMarca() {
		return marca;
	}

	/**
	 * @return the modelo
	 */
	public final HasValue<String> getModelo() {
		return modelo;
	}

	/**
	 * @return the tipo
	 */
	public final HasValue<String> getTipo() {
		return tipo;
	}

	/**
	 * @return the centro
	 */
	public final HasValue<String> getCentro() {
		return centro;
	}

	/**
	 * @return the sociedad
	 */
	public final HasValue<String> getSociedad() {
		return sociedad;
	}

	/**
	 * @return the departamento
	 */
	public final TextBox getDepartamento() {
		return departamento;
	}

	/**
	 * @return the ubicacion
	 */
	public final HasValue<String> getUbicacion() {
		return ubicacion;
	}

	/**
	 * @return the fechaCompra
	 */
	public final DateBox getFechaCompra() {
		return fechaCompra;
	}

	/**
	 * @return the fechaFinGarantia
	 */
	public final DateBox getFechaFinGarantia() {
		return fechaFinGarantia;
	}

	/**
	 * @return the proveedor
	 */
	public final HasValue<String> getProveedor() {
		return proveedor;
	}

	/**
	 * @return the mantenimiento_0
	 */
	public final Boolean getMantenimiento() {
		return mantenimiento_0.getValue();
	}

	/**
	 * @param mantenimiento_0 the mantenimiento_0 to set
	 */
	public final void setMantenimiento(Boolean mantenimiento) {
		this.mantenimiento_0.setValue(mantenimiento);
		
		if(!mantenimiento)
			this.mantenimiento_1.setValue(true);
	}

	/**
	 * @return the observaciones
	 */
	public final HasValue<String> getObservaciones() {
		return observaciones;
	}

	@Override
	public Panel getTable() {
		return this.table;
	}
}
