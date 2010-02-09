/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.client.event.LoadingEvent;
import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.parte.event.AddPartesEvent;
import cbmarc.inventory.client.mvp.parte.event.EditPartesEvent;
import cbmarc.inventory.shared.entity.Parte;

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
public class ListPartePresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getTable();

		void setData(List<Parte> data);
		List<Integer> getSelectedRows();
		
		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private final ParteServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private List<Parte> partes;

	/**
	 * @param eventBus
	 * @param view
	 */
	public ListPartePresenter(ParteServiceAsync rpcService, 
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
				eventBus.fireEvent(new AddPartesEvent());
			}

		});
		
		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				List<Integer> selectedRows = display.getSelectedRows();
				
				if(selectedRows.isEmpty()) {
					Window.alert("No hay ningun elemento seleccionado");
				} else { 
					if(Window.confirm("Borrar los elementos seleccionados ?"))
						onDeleteSelected(selectedRows);
				}
			}
			
		});
		
		display.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				if(selectedRow >= 0) {
					Long id = partes.get(selectedRow).getId();
					
					eventBus.fireEvent(new EditPartesEvent(id));
				}
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void onDeleteSelected(List<Integer> selectedRows) {
		ArrayList<Long> ids = new ArrayList<Long>();
		
		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(partes.get(selectedRows.get(i)).getId());
		}
		
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.delete(ids, new AsyncCallback<List<Parte>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("ERROR: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Parte> result) {
				eventBus.fireEvent(new LoadingEvent(false));
				partes = result;
				display.setData(partes);
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

		getPartes();
	}
	
	private void getPartes() {
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.select(new AsyncCallback<List<Parte>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("Error fetching Partes: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Parte> result) {
				eventBus.fireEvent(new LoadingEvent(false));
				partes = result;
				display.setData(partes);
			}
			
		});
	}
}
