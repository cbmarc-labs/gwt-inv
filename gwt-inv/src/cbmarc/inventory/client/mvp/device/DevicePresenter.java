/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.device.event.AddDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.AddDeviceEventHandler;
import cbmarc.inventory.client.mvp.device.event.CreatedDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.CreatedDeviceEventHandler;
import cbmarc.inventory.client.mvp.device.event.DeleteDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.DeleteDeviceEventHandler;
import cbmarc.inventory.client.mvp.device.event.EditCancelledDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.EditCancelledDeviceEventHandler;
import cbmarc.inventory.client.mvp.device.event.EditDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.EditDeviceEventHandler;
import cbmarc.inventory.client.mvp.device.event.SaveDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.SaveDeviceEventHandler;
import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class DevicePresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final DeviceServiceAsync rpcService;
	private final HandlerManager eventBus;
	protected final Display display;
	
	protected final ListDevicePresenter list;
	protected final EditDevicePresenter edit;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public DevicePresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    this.rpcService = GWT.create(DeviceService.class);
	    
		this.list = new ListDevicePresenter(
				rpcService, eventBus, new ListDeviceView());
		this.edit = new EditDevicePresenter(
				rpcService, eventBus, new EditDeviceView());
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {		
		eventBus.addHandler(AddDeviceEvent.TYPE,
				new AddDeviceEventHandler() {
			public void onAdd(AddDeviceEvent event) {
				doAdd();
			}
	    });
		
		eventBus.addHandler(EditCancelledDeviceEvent.TYPE,
				new EditCancelledDeviceEventHandler() {

			@Override
			public void onEditCancelled(EditCancelledDeviceEvent event) {
				list.go(display.getContent());
			}
			
	    });
		
		eventBus.addHandler(CreatedDeviceEvent.TYPE, 
				new CreatedDeviceEventHandler() {

			@Override
			public void onCreated(CreatedDeviceEvent event) {
				list.go(display.getContent());
			}
			
		});
		
		eventBus.addHandler(EditDeviceEvent.TYPE, 
				new EditDeviceEventHandler() {

			@Override
			public void onEdit(EditDeviceEvent event) {
				doEdit(event.getKey());
			}
			
		});
		
		eventBus.addHandler(DeleteDeviceEvent.TYPE, 
				new DeleteDeviceEventHandler() {

			@Override
			public void onDelete(DeleteDeviceEvent event) {
				list.deleteSelected();
			}
			
		});
		
		eventBus.addHandler(SaveDeviceEvent.TYPE, 
				new SaveDeviceEventHandler() {

			@Override
			public void onSave(SaveDeviceEvent event) {
				edit.doSave();
			}
			
		});
	}
	
	/**
	 * 
	 */
	protected void doAdd() {
		edit.setBean(new Device());
		edit.go(display.getContent());
	}
	
	/**
	 * @param id
	 */
	private void doEdit(String key) {
		rpcService.selectByKey(key, new AsyncCallback<Device>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("NO SE HA PODIDO");
			}

			@Override
			public void onSuccess(Device bean) {
				if(bean != null) {
					edit.setBean(bean);
					edit.go(display.getContent());
				} else {
					Window.alert("NO SE HA PODIDO");
				}
			}
			
		});
	}

	/**
	 * @return the list
	 */
	public ListDevicePresenter getList() {
		return list;
	}

	/**
	 * @return the edit
	 */
	public EditDevicePresenter getEdit() {
		return edit;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		list.go(display.getContent());
	    container.add(display.asWidget());
	}

}
