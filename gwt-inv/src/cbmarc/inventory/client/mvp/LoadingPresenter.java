/**
 * 
 */
package cbmarc.inventory.client.mvp;

import cbmarc.inventory.client.event.LoadingEvent;
import cbmarc.inventory.client.event.LoadingEventHandler;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class LoadingPresenter implements Presenter {
	public interface Display {
		public void startProcessing();
		public void stopProcessing();
		
		public void showWidget();
	}
	
	private final HandlerManager eventBus;
	private final Display display;

	public LoadingPresenter(HandlerManager eventBus, Display view) {
		this.eventBus = eventBus;
	    this.display = view;
	    
	    bind();
	}

	protected void bind() {
		eventBus.addHandler(LoadingEvent.TYPE, 
				new LoadingEventHandler() {

					@Override
					public void onLoading(boolean show) {
						if(show) {
							display.startProcessing();
						} else {
							display.stopProcessing();
						}
					}			
		});
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
