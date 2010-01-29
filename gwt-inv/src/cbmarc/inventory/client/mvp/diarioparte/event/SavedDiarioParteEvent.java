package cbmarc.inventory.client.mvp.diarioparte.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavedDiarioParteEvent 
		extends GwtEvent<SavedDiarioParteEventHandler> {
	public static Type<SavedDiarioParteEventHandler> TYPE = 
		new Type<SavedDiarioParteEventHandler>();
  
	@Override
	public Type<SavedDiarioParteEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SavedDiarioParteEventHandler handler) {
		handler.onSaved(this);
	}
}
