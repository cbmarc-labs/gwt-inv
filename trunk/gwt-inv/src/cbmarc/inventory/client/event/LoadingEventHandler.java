/**
 * 
 */
package cbmarc.inventory.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author MCOSTA
 *
 */
public interface LoadingEventHandler extends EventHandler {
	public void onLoading(boolean isComplete);
}
