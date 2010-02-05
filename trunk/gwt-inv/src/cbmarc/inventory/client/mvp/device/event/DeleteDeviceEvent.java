package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteDeviceEvent extends GwtEvent<DeleteDeviceEventHandler> {
  public static Type<DeleteDeviceEventHandler> TYPE = 
	  new Type<DeleteDeviceEventHandler>();
  
  @Override
  public Type<DeleteDeviceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DeleteDeviceEventHandler handler) {
    handler.onDelete(this);
  }
}
