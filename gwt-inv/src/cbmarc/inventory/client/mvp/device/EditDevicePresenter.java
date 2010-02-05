/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.Date;

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
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditDevicePresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getListButton();
		
		HasValue<String> getId();
		HasValue<Date> getDate();
		
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

	public boolean doSave() {
		// TODO fill bean from display
		
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
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
		if(bean.getId() != null) {
			display.getId().setValue(bean.getId().toString());
			display.getDate().setValue(bean.getDate());
		}
		
	    container.add(display.asWidget());
	    // TODO set focus on some field
	}

}
