package cbmarc.inventory.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_inv implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(eventBus);

	    //RootPanel.get().setStyleName("body");
	    appViewer.go(RootPanel.get());
	    //appViewer.go(RootLayoutPanel.get());
	}
}
