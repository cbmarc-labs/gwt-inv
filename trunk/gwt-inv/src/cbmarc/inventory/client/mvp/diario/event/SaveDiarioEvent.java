package cbmarc.inventory.client.mvp.diario.event;

import cbmarc.inventory.shared.entity.Diario;

import com.google.gwt.event.shared.GwtEvent;

public class SaveDiarioEvent extends GwtEvent<SaveDiarioEventHandler> {
	public static Type<SaveDiarioEventHandler> TYPE = 
		new Type<SaveDiarioEventHandler>();

	@Override
	public Type<SaveDiarioEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SaveDiarioEventHandler handler) {
	  handler.onSave(this);
	}
}
