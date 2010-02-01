/**
 * 
 */
package cbmarc.inventory.client.mvp.diarioparte;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.client.event.LoadingEvent;
import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.diarioparte.event.AddDiarioParteEvent;
import cbmarc.inventory.client.mvp.diarioparte.event.EditDiarioParteEvent;
import cbmarc.inventory.shared.entity.DiarioParte;

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
public class ListDiarioPartePresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getTable();

		void setData(List<DiarioParte> data);
		List<Integer> getSelectedRows();
		
		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private final DiarioParteServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private Long numParte = null;
	private List<DiarioParte> lista;

	/**
	 * @param eventBus
	 * @param view
	 */
	public ListDiarioPartePresenter(DiarioParteServiceAsync rpcService, 
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
				eventBus.fireEvent(new AddDiarioParteEvent());
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
					Long id = lista.get(selectedRow).getId();
					eventBus.fireEvent(new EditDiarioParteEvent(id));
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
			ids.add(lista.get(selectedRows.get(i)).getId());
		}
		
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.delete(ids, new AsyncCallback<List<DiarioParte>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("ERROR: " + caught.toString());
			}

			@Override
			public void onSuccess(List<DiarioParte> result) {
				setData(result);
			}
			
		});
	}
	
	private void setData(List<DiarioParte> result) {
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
	public Long getNumParte() {
		return numParte;
	}

	/**
	 * @param numParte the numParte to set
	 */
	public void setNumParte(Long numParte) {
		this.numParte = numParte;
	}

	private void getData() {
		String filter = null;
		
		if(this.numParte != null)
			filter = "par_id==" + this.numParte;
		
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.select(filter, new AsyncCallback<List<DiarioParte>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("Error fetching Partes: " + caught.toString());
			}

			@Override
			public void onSuccess(List<DiarioParte> result) {
				setData(result);
			}
			
		});
	}
}
