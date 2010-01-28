/**
 * 
 */
package cbmarc.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author MCOSTA
 *
 */
public class LoadingEvent extends GwtEvent<LoadingEventHandler> {
	public static Type<LoadingEventHandler> TYPE = 
		new Type<LoadingEventHandler>();
	
	private final boolean show;
	
	public LoadingEvent(final boolean show) {
		this.show = show;
	}
	
	public boolean getShow() {
		return show;
	}

	@Override
	protected void dispatch(LoadingEventHandler handler) {
		handler.onLoading(show);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoadingEventHandler> 
			getAssociatedType() {
		return TYPE;
	}
}
