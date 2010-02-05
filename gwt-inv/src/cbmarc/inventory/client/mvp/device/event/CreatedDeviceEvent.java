package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class CreatedDeviceEvent 
		extends GwtEvent<CreatedDeviceEventHandler> {
	public static Type<CreatedDeviceEventHandler> TYPE = 
		new Type<CreatedDeviceEventHandler>();
  
	@Override
	public Type<CreatedDeviceEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreatedDeviceEventHandler handler) {
		handler.onCreated(this);
	}
}
