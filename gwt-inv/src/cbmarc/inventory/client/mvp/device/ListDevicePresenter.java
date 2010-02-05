/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.client.event.LoadingEvent;
import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.device.event.AddDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.DeleteDeviceEvent;
import cbmarc.inventory.client.mvp.device.event.EditDeviceEvent;
import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class ListDevicePresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getTable();

		void setData(List<Device> data);
		List<Integer> getSelectedRows();
		
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
	public ListDevicePresenter(DeviceServiceAsync rpcService, 
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
		display.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddDeviceEvent());
			}

		});
		
		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new DeleteDeviceEvent());
			}
			
		});
		
		display.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				if(selectedRow > 0) {
					Long id = lista.get(selectedRow - 1).getId();
					eventBus.fireEvent(new EditDeviceEvent(id));
				}
			}
			
		});
	}
	
	/**
	 * 
	 */
	public void deleteSelected() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<Long> ids = new ArrayList<Long>();

		if(selectedRows.isEmpty()) {
			Window.alert("No hay ningun elemento seleccionado");
		} else {
			if(Window.confirm("Borrar los elementos seleccionados ?")) {
				for (int i = 0; i < selectedRows.size(); ++i) {
					if(selectedRows.get(i) > 0)
						ids.add(lista.get(selectedRows.get(i) - 1).getId());
				}
		
				rpcService.delete(ids, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR: " + caught.toString());
					}

					@Override
					public void onSuccess(Void result) {
						getData();
					}
			
				});
			}
		}
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
