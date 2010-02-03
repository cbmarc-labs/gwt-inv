package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.GwtEvent;

public class CreatedDiarioEvent 
		extends GwtEvent<CreatedDiarioEventHandler> {
	public static Type<CreatedDiarioEventHandler> TYPE = 
		new Type<CreatedDiarioEventHandler>();
  
	@Override
	public Type<CreatedDiarioEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreatedDiarioEventHandler handler) {
		handler.onCreated(this);
	}
}
