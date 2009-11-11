/**
 * 
 */
package cbmarc.inventory.client.mvp;

import cbmarc.framework.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;

/**
 * @author MCOSTA
 *
 */
public class TopPresenter extends AbstractPresenter<TopPresenter.Display> {
	public interface Display extends AbstractPresenter.Display {
		public HasClickHandlers getTopHyperlink();
	}

	public static final Place PLACE = new Place("Top");

	@Inject
	public TopPresenter(final Display display, final EventBus eventBus) {
		super(display, eventBus);
		bind();
	}

	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Override
	protected void onBind() {
		display.getTopHyperlink().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				Window.alert("Top Pressed");
			}
			
		});
	}
}
