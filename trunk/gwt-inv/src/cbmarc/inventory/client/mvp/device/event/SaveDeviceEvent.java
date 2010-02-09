package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class SaveDeviceEvent extends GwtEvent<SaveDeviceEventHandler> {
	public static Type<SaveDeviceEventHandler> TYPE = 
		new Type<SaveDeviceEventHandler>();

	@Override
	public Type<SaveDeviceEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SaveDeviceEventHandler handler) {
	  handler.onSave(this);
	}
}
