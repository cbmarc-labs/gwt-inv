/**
 * 
 */
package cbmarc.inventory.shared.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author MCOSTA
 *
 */
public interface SendArticleEventHandler extends EventHandler {
	public void onSendArticle(SendArticleEvent event);
}
