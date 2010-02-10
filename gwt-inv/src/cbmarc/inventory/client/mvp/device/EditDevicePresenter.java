/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.device.event.CreatedDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.EditCancelledDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.SaveDeviceEvent;
import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * @author MCOSTA
 *
 */
public class EditDevicePresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getListButton();
		
		TextBox getSn();
		HasValue<String> getNombre();
		HasValue<String> getMarca();
		HasValue<String> getModelo();
		HasValue<String> getTipo();
		HasValue<String> getCentro();
		HasValue<String> getSociedad();
		HasValue<String> getDepartamento();
		HasValue<String> getUbicacion();
		DateBox getFechaCompra();
		DateBox getFechaFinGarantia();
		HasValue<String> getProveedor();
		Boolean getMantenimiento();
		void setMantenimiento(Boolean mantenimiento);
		HasValue<String> getObservaciones();
		
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		
		void reset();
		
		Widget asWidget();
	}
	
	private final DeviceServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private Device bean = new Device();
	
	/**
	 * @param rpcService
	 * @param eventBus
	 * @param view
	 */
	public EditDevicePresenter(DeviceServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
		display.getListButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledDeviceEvent());
			}
	    	
	    });
		
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledDeviceEvent());
			}
			
		});
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new SaveDeviceEvent());
			}
			
		});
	}
	
	/**
	 * @return
	 */
	public Device getBean() {
		return bean;
	}

	/**
	 * @param bean
	 */
	public void setBean(Device bean) {
		this.bean = bean;
	}
	
	/**
	 * 
	 */
	private void fillBean() {
		this.bean.setSn(display.getSn().getValue());
		this.bean.setNombre(display.getNombre().getValue());
		this.bean.setMarca(display.getMarca().getValue());
		this.bean.setModelo(display.getModelo().getValue());
		this.bean.setTipo(display.getTipo().getValue());
		this.bean.setCentro(display.getCentro().getValue());
		this.bean.setSociedad(display.getSociedad().getValue());
		// TODO implements foreign departamento
		this.bean.setDepartamento(display.getDepartamento().getValue());
		this.bean.setUbicacion(display.getUbicacion().getValue());
		this.bean.setFechaCompra(display.getFechaCompra().getValue());
		this.bean.setFechaFinGarantia(display.getFechaFinGarantia().getValue());
		this.bean.setProveedor(display.getProveedor().getValue());
		this.bean.setMantenimiento(display.getMantenimiento());
		this.bean.setObservaciones(display.getObservaciones().getValue());
	}

	public boolean doSave() {
		fillBean();
		
		rpcService.save(bean, new AsyncCallback<Device>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(Device result) {
				eventBus.fireEvent(new CreatedDeviceEvent());
			}
			
		});
		
		return true;
	}
	
	/**
	 * 
	 */
	private void fillDisplay() {
		display.getSn().setValue(bean.getSn());
		display.getNombre().setValue(bean.getNombre());
		display.getMarca().setValue(bean.getMarca());
		display.getModelo().setValue(bean.getModelo());
		display.getTipo().setValue(bean.getTipo());
		display.getCentro().setValue(bean.getCentro());
		display.getSociedad().setValue(bean.getSociedad());
		display.getDepartamento().setValue(bean.getSociedad());
		display.getUbicacion().setValue(bean.getUbicacion());
		display.getFechaCompra().setValue(bean.getFechaCompra());
		display.getFechaFinGarantia().setValue(bean.getFechaFinGarantia());
		display.getProveedor().setValue(bean.getProveedor());
		display.setMantenimiento(bean.getMantenimiento());
		display.getObservaciones().setValue(bean.getObservaciones());
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
		if(bean.getKey() != null) 
			fillDisplay();
		
	    container.add(display.asWidget());
	    display.getSn().setFocus(true);
	}

}
