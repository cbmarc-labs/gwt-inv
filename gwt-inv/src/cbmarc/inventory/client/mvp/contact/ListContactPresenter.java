/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.client.event.LoadingEvent;
import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.contact.event.AddContactEvent;
import cbmarc.inventory.client.mvp.contact.event.EditContactEvent;
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
	
	private List<Contact> contacts;

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
						onDeleteSelected(selectedRows);
				}
			}
			
		});
		
		display.getcontactsTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				
				if(selectedRow >= 0) {
					Long id = contacts.get(selectedRow).getId();
					eventBus.fireEvent(new EditContactEvent(id));
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
			ids.add(contacts.get(selectedRows.get(i)).getId());
		}
		
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.delete(ids, new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("ERROR: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Contact> result) {
				eventBus.fireEvent(new LoadingEvent(false));
				contacts = result;
				display.setData(contacts);
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

		getContacts();
	}
	
	private void getContacts() {
		eventBus.fireEvent(new LoadingEvent(true));
		rpcService.select(new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				eventBus.fireEvent(new LoadingEvent(false));
				Window.alert("Error fetching contacts: " + caught.toString());
			}

			@Override
			public void onSuccess(List<Contact> result) {
				eventBus.fireEvent(new LoadingEvent(false));
				contacts = result;
				display.setData(contacts);
			}
			
		});
	}
}
