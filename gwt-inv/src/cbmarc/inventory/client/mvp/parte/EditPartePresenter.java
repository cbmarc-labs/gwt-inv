/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;


import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.device.DeviceService;
import cbmarc.inventory.client.mvp.device.DeviceServiceAsync;
import cbmarc.inventory.client.mvp.device.SelectDevicePresenter;
import cbmarc.inventory.client.mvp.device.SelectDeviceView;
import cbmarc.inventory.client.mvp.device.event.SelectCancelledDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.SelectCancelledDeviceEventHandler;
import cbmarc.inventory.client.mvp.device.event.SelectDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.SelectDeviceEventHandler;
import cbmarc.inventory.client.mvp.diario.DiarioService;
import cbmarc.inventory.client.mvp.diario.DiarioServiceAsync;
import cbmarc.inventory.client.mvp.diario.DiarioView;
import cbmarc.inventory.client.mvp.parte.event.EditCancelledPartesEvent;
import cbmarc.inventory.client.mvp.parte.event.SavedPartesEvent;
import cbmarc.inventory.shared.entity.Parte;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditPartePresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getListButton();
		
	    TextBox getAtu();
	    TextBox getFecha();
	    /*HasValue<String> getHora();
	    HasValue<String> getNumparte();
	    HasValue<String> getDis_id();
	    HasValue<String> getDis_sn();
	    HasValue<String> getDis_nombre();
	    HasValue<String> getDis_marca();
	    HasValue<String> getDis_modelo();
	    HasValue<String> getDis_tipo();
	    HasValue<String> getDis_centro();
	    HasValue<String> getDis_departamento();
	    HasValue<String> getDis_ubicacion();
	    HasValue<String> getDis_fecha_compra();
	    HasValue<String> getDis_fin_garantia();
	    HasValue<String> getDis_proveedor();
	    HasValue<String> getDis_mantenimiento();
	    HasValue<String> getDis_observaciones();*/
	    
	    HasValue<String> getDis_id();
		
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		
		HasClickHandlers getSelectDispositivoButton();
		
		HasWidgets getDiario();
		Panel getDispositivo();
		Panel getDispositivoFields();
		DisclosurePanel getDiarioDisclosurePanel();
		
		void reset();
		
		Widget asWidget();
	}
	
	private Parte parte;
	private final DiarioPartePresenter diarioPresenter;
	
	private final HandlerManager deviceHandlerManager;
	private final DeviceServiceAsync deviceServiceAsync;
	private final SelectDevicePresenter selectDevicePresenter;
	private final SelectDeviceView selectDeviceView;
	
	private final ParteServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private final DiarioServiceAsync diarioParterpcService;
	
	/**
	 * @param rpcService
	 * @param eventBus
	 * @param view
	 */
	public EditPartePresenter(ParteServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    diarioParterpcService = GWT.create(DiarioService.class);
	    
	    parte = new Parte();
	    diarioPresenter = new DiarioPartePresenter(eventBus, new DiarioView());
	    
	    deviceHandlerManager = new HandlerManager(null);
	    deviceServiceAsync = GWT.create(DeviceService.class);
	    selectDeviceView = new SelectDeviceView();
	    selectDevicePresenter = new SelectDevicePresenter(
	    		deviceServiceAsync, deviceHandlerManager, selectDeviceView);
	    
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
		display.getListButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledPartesEvent());
			}
	    	
	    });
		
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledPartesEvent());
			}
			
		});
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSave();
			}
			
		});
		
		display.getSelectDispositivoButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				display.getDispositivoFields().setVisible(false);
				display.getDispositivo().setVisible(true);
			}
			
		});
		
		deviceHandlerManager.addHandler(SelectCancelledDeviceEvent.TYPE, 
				new SelectCancelledDeviceEventHandler() {

			@Override
			public void onSelectCancelled(SelectCancelledDeviceEvent event) {
				display.getDispositivo().setVisible(false);
				display.getDispositivoFields().setVisible(true);
			}
			
		});
		
		deviceHandlerManager.addHandler(SelectDeviceEvent.TYPE,
				new SelectDeviceEventHandler() {

					@Override
					public void onSelect(SelectDeviceEvent event) {
						display.getDispositivo().setVisible(false);
						display.getDispositivoFields().setVisible(true);
						display.getDis_id().setValue(event.getBean().getKey());
					}
			
		});
	}
	
	/**
	 * @return the contact
	 */
	public Parte getParte() {
		return parte;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setParte(Parte parte) {
		this.parte = parte;
	}
	
	/**
	 * 
	 */
	private void doSave() {
		// TODO implement others elements
		this.parte.setAtu(display.getAtu().getValue());
		
		rpcService.save(this.parte, new AsyncCallback<Parte>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}
			
			@Override
			public void onSuccess(Parte result) {
				eventBus.fireEvent(new SavedPartesEvent());
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		display.reset();
		display.getAtu().setValue(this.parte.getAtu());
		selectDevicePresenter.go(display.getDispositivo());
		display.getDispositivo().setVisible(false);
		
		if(parte.getId() == null) {
			display.getDiario().clear();
			display.getDiarioDisclosurePanel().setVisible(false);
		} else {
			diarioPresenter.getList().setFilter("parte==" + parte.getId());
			diarioPresenter.setParte(parte.getId());
			diarioPresenter.go(display.getDiario());
			display.getDiarioDisclosurePanel().setVisible(true);
		}
		
	    container.add(display.asWidget());
	    display.getAtu().setFocus(true);
	}

}
