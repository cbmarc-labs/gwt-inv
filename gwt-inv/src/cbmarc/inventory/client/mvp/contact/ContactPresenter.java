/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;


import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.contact.event.AddContactEvent;
import cbmarc.inventory.client.mvp.contact.event.AddContactEventHandler;
import cbmarc.inventory.client.mvp.contact.event.EditContactCancelledEvent;
import cbmarc.inventory.client.mvp.contact.event.EditContactCancelledEventHandler;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
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
	
	private final HandlerManager eventBus;
	private final Display display;
	
	private ListContactPresenter lcp;
	private EditContactPresenter ecp;
	
	public ContactPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    
		this.lcp = new ListContactPresenter(eventBus, new ListContactView());
		this.ecp = new EditContactPresenter(eventBus, new EditContactView());
		
	    bind();
	}
	
	public void bind() {
		eventBus.addHandler(AddContactEvent.TYPE,
				new AddContactEventHandler() {
			public void onAddContact(AddContactEvent event) {
				doAddNewContact();
			}
	    });
		
		eventBus.addHandler(EditContactCancelledEvent.TYPE,
				new EditContactCancelledEventHandler() {

			@Override
			public void onEditContactCancelled(EditContactCancelledEvent event) {
				doEditContactCancelled();
			}
	    });
	}
	
	private void doAddNewContact() {
		ecp.go(display.getContent());
	}
	
	private void doEditContactCancelled() {
		lcp.go(display.getContent());
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		lcp.go(display.getContent());
	    container.add(display.asWidget());
	}

}
