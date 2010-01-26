/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;


import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.contact.event.AddContactEvent;
import cbmarc.inventory.client.mvp.contact.event.AddContactEventHandler;
import cbmarc.inventory.client.mvp.contact.event.EditCancelledContactEvent;
import cbmarc.inventory.client.mvp.contact.event.EditCancelledContactEventHandler;
import cbmarc.inventory.client.mvp.contact.event.EditContactEvent;
import cbmarc.inventory.client.mvp.contact.event.EditContactEventHandler;
import cbmarc.inventory.client.mvp.contact.event.SavedContactEvent;
import cbmarc.inventory.client.mvp.contact.event.SavedContactEventHandler;
import cbmarc.inventory.shared.entity.Contact;

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
public class ContactPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final ContactsServiceAsync rpcService; 
	private final HandlerManager eventBus;
	private final Display display;
	
	private final ListContactPresenter listContact;
	private final EditContactPresenter editContact;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public ContactPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    this.rpcService = GWT.create(ContactsService.class);
	    
		this.listContact = new ListContactPresenter(
				rpcService, eventBus, new ListContactView());
		this.editContact = new EditContactPresenter(
				rpcService, eventBus, new EditContactView());
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
		eventBus.addHandler(AddContactEvent.TYPE,
				new AddContactEventHandler() {
			public void onAddContact(AddContactEvent event) {
				doAddNewContact();
			}
	    });
		
		eventBus.addHandler(EditCancelledContactEvent.TYPE,
				new EditCancelledContactEventHandler() {

			@Override
			public void onEditContactCancelled(EditCancelledContactEvent event) {
				doEditContactCancelled();
			}
	    });
		
		eventBus.addHandler(SavedContactEvent.TYPE, 
				new SavedContactEventHandler() {

			@Override
			public void onSavedContact(SavedContactEvent event) {
				doEditContactCancelled();
			}
			
		});
		
		eventBus.addHandler(EditContactEvent.TYPE, 
				new EditContactEventHandler() {

			@Override
			public void onEditContact(EditContactEvent event) {
				doEditContact(event.getId());
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doAddNewContact() {
		editContact.setContact(new Contact());
		editContact.go(display.getContent());
	}
	
	/**
	 * 
	 */
	private void doEditContactCancelled() {
		listContact.go(display.getContent());
	}
	
	/**
	 * @param id
	 */
	private void doEditContact(Long id) {
		rpcService.select(id, new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("NO SE HA PODIDO");
			}

			@Override
			public void onSuccess(Contact result) {
				if(result != null) {
					editContact.setContact(result);
					editContact.go(display.getContent());
				} else {
					Window.alert("NO SE HA PODIDO");
				}
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		listContact.go(display.getContent());
	    container.add(display.asWidget());
	}

}
