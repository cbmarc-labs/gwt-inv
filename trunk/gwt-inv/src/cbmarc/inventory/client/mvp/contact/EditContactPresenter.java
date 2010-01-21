/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;


import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.contact.event.EditContactCancelledEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditContactPresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getTestButton();
		Widget asWidget();
	}
	
	private final HandlerManager eventBus;
	private final Display display;
	
	public EditContactPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    bind();
	}
	
	public void bind() {
		display.getTestButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditContactCancelledEvent());
			}
	    	
	    });
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

}
