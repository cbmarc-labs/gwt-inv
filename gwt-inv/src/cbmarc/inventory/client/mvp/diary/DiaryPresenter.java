/**
 * 
 */
package cbmarc.inventory.client.mvp.diary;

import cbmarc.inventory.client.mvp.Presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class DiaryPresenter implements Presenter {
	
	public interface Display {
		Widget asWidget();
	}
	
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	private final Display display;
	
	public DiaryPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    bind();
	}
	
	public void bind() {
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

}
