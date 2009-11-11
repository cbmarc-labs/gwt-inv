/**
 * 
 */
package cbmarc.inventory.shared.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author MCOSTA
 *
 */
public class SendArticleEvent extends GwtEvent<SendArticleEventHandler> {
	public static Type<SendArticleEventHandler> TYPE = 
		new Type<SendArticleEventHandler>();

	@Override
	protected void dispatch(SendArticleEventHandler handler) {
		handler.onSendArticle(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SendArticleEventHandler> 
			getAssociatedType() {
		return TYPE;
	}
}
