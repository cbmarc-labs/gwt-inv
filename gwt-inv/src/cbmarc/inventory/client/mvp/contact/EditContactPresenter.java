/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;


import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.contact.event.EditCancelledContactEvent;
import cbmarc.inventory.client.mvp.contact.event.SavedContactEvent;
import cbmarc.inventory.shared.entity.Contact;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditContactPresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getListButton();
		
	    HasValue<String> getFirstName();
	    HasValue<String> getLastName();
	    HasValue<String> getEmailAddress();
	    
	    Focusable getFirstNameFocus();
		
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		
		void reset();
		
		Widget asWidget();
	}
	
	private Contact contact;
	
	private final ContactsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	/**
	 * @param rpcService
	 * @param eventBus
	 * @param view
	 */
	public EditContactPresenter(ContactsServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    contact = new Contact();
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
		display.getListButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledContactEvent());
			}
	    	
	    });
		
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledContactEvent());
			}
			
		});
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSave();
			}
			
		});
	}
	
	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	/**
	 * 
	 */
	private void doSave() {
		contact.setFirstName(display.getFirstName().getValue());
		contact.setLastName(display.getLastName().getValue());
		contact.setEmailAddress(display.getEmailAddress().getValue());
		
		rpcService.save(contact, new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(Contact result) {
				eventBus.fireEvent(new SavedContactEvent());
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		display.reset();
		display.getFirstName().setValue(contact.getFirstName());
		display.getLastName().setValue(contact.getLastName());
		display.getEmailAddress().setValue(contact.getEmailAddress());
		
	    container.add(display.asWidget());
	    display.getFirstNameFocus().setFocus(true);
	}

}
