/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import cbmarc.inventory.client.mvp.Presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class DepartamentoPresenter implements Presenter {
	
	public interface Display {
		Widget asWidget();
	}
	
	private final HandlerManager eventBus;
	private final Display display;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public DepartamentoPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	}

}
