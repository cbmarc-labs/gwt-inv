/**
 * 
 */
package cbmarc.inventory.client.mvp;

import cbmarc.framework.client.mvp.AbstractPresenter;

import com.google.inject.Inject;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;

/**
 * @author MCOSTA
 *
 */
public class BottomPresenter 
		extends AbstractPresenter<BottomPresenter.Display> {
	public interface Display extends AbstractPresenter.Display {
	}

	public static final Place PLACE = new Place("Bottom");

	@Inject
	public BottomPresenter(final Display display, final EventBus eventBus) {
		super(display, eventBus);
		bind();
	}

	@Override
	public Place getPlace() {
		return PLACE;
	}
}
