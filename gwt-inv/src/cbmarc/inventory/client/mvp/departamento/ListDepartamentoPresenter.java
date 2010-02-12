/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.client.event.LoadingEvent;
import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.departamento.event.AddDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.DeleteDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.EditDepartamentoEvent;
import cbmarc.inventory.shared.entity.Departamento;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 * 
 */
public class ListDepartamentoPresenter implements Presenter {

	public interface Display {
		Panel getToolbar();
		
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getTable();

		void setData(List<Departamento> data);
		List<Integer> getSelectedRows();
		
		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private final DepartamentoServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private String filter = null;
	private List<Departamento> lista;

	/**
	 * @param eventBus
	 * @param view
	 */
	public ListDepartamentoPresenter(DepartamentoServiceAsync rpcService, 
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
				eventBus.fireEvent(new AddDepartamentoEvent());
			}

		});
		
		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new DeleteDepartamentoEvent());
			}
			
		});
		
		display.getTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				if(selectedRow > 0) {
					String key = lista.get(selectedRow - 1).getEncodedKey();
					eventBus.fireEvent(new EditDepartamentoEvent(key));
				}
			}
			
		});
	}
	
	/**
	 * 
	 */
	public void deleteSelected() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> keys = new ArrayList<String>();

		if(selectedRows.isEmpty()) {
			Window.alert("No hay ningun elemento seleccionado");
		} else {
			if(Window.confirm("Borrar los elementos seleccionados ?")) {
				for (int i = 0; i < selectedRows.size(); ++i) {
					if(selectedRows.get(i) > 0)
						keys.add(lista.get(selectedRows.get(i) - 1).getEncodedKey());
				}
		
				rpcService.delete(keys, new AsyncCallback<Void>() {

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
	private void setData(List<Departamento> result) {
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
	
	public void setToolbarVisible(boolean visible) {
		display.getToolbar().setVisible(visible);
	}

	/**
	 * 
	 */
	private void getData() {		
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.select(this.filter, new AsyncCallback<List<Departamento>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("Error fetching Partes: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Departamento> result) {
				setData(result);
			}
			
		});
	}
}
