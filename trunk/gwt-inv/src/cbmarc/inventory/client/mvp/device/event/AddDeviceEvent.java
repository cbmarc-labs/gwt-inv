package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddDeviceEvent extends GwtEvent<AddDeviceEventHandler> {
  public static Type<AddDeviceEventHandler> TYPE = 
	  new Type<AddDeviceEventHandler>();
  
  @Override
  public Type<AddDeviceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddDeviceEventHandler handler) {
    handler.onAdd(this);
  }
}
