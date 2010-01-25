/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.contact.event.AddContactEvent;
import cbmarc.inventory.shared.entity.Contact;

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
public class ListContactPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getcontactsTable();

		void setData(List<Contact> data);
		List<Integer> getSelectedRows();
		
		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private final ContactsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private ArrayList<Contact> contacts;

	/**
	 * @param eventBus
	 * @param view
	 */
	public ListContactPresenter(ContactsServiceAsync rpcService, 
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
				eventBus.fireEvent(new AddContactEvent());
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
						onDeleteSelected();
				}
			}
			
		});
		
		display.getcontactsTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				Window.alert("ROW => " + selectedRow);
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void onDeleteSelected() {
		Window.alert("SE HAN BORRAO LOS ELEMENTOS");
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

		getContacts();
	}
	
	private void getContacts() {
		rpcService.getContacts(new AsyncCallback<ArrayList<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching contacts: " + caught.toString());
			}

			@Override
			public void onSuccess(ArrayList<Contact> result) {
				contacts = result;
				display.setData(contacts);
			}
			
		});
	}
}
