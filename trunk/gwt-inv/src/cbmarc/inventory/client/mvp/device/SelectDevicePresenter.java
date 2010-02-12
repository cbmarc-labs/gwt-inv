/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.List;

import cbmarc.inventory.client.event.LoadingEvent;
import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.device.event.SelectCancelledDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.SelectDeviceEvent;
import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class SelectDevicePresenter implements Presenter {

	public interface Display {
		HasClickHandlers getCancelButton();
		HasClickHandlers getTable();

		void setData(List<Device> data);
		
		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private final DeviceServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private String filter = null;
	private List<Device> lista;

	/**
	 * @param eventBus
	 * @param view
	 */
	public SelectDevicePresenter(DeviceServiceAsync rpcService, 
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
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new SelectCancelledDeviceEvent());
			}

		});
		
		display.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				if(selectedRow > 0) {
					Device bean = lista.get(selectedRow - 1);
					eventBus.fireEvent(new SelectDeviceEvent(bean));
				}
			}
			
		});
	}
	
	/**
	 * @param result
	 */
	private void setData(List<Device> result) {
		eventBus.fireEvent(new LoadingEvent(false));
		
		lista = result;
		display.setData(lista);
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

		getData();
	}
	
	/**
	 * @return the numParte
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param numParte the numParte to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * 
	 */
	private void getData() {		
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.select(this.filter, new AsyncCallback<List<Device>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("Error fetching Partes: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Device> result) {
				setData(result);
			}
			
		});
	}
}
