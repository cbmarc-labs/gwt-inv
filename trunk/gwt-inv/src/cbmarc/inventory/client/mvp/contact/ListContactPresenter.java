/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.contact.event.AddContactEvent;

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
public class ListContactPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddButton();

		void setData(List<String> data);

		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final Display display;

	/**
	 * @param eventBus
	 * @param view
	 */
	public ListContactPresenter(HandlerManager eventBus, Display view) {
		this.eventBus = eventBus;
		this.display = view;
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
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		bind();

		container.clear();
		container.add(display.asWidget());

		fetchContactDetails();
	}

	/**
	 * 
	 */
	private void fetchContactDetails() {
		List<String> data = new ArrayList<String>();
		data.add("ESTE ES UNO");
		data.add("ESTE ES DOS");
		data.add("ESTE ES TRES");
		data.add("ESTE ES CUATRO");
		data.add("ESTE ES CINCO");
		data.add("ESTE ES SEIS");
		data.add("ESTE ES SIETE");

		display.setData(data);
	}

}
