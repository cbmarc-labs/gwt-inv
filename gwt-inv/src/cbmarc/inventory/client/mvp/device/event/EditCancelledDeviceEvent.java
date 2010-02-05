package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditCancelledDeviceEvent 
		extends GwtEvent<EditCancelledDeviceEventHandler> {
  public static Type<EditCancelledDeviceEventHandler> TYPE = 
	  new Type<EditCancelledDeviceEventHandler>();
  
  @Override
  public Type<EditCancelledDeviceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditCancelledDeviceEventHandler handler) {
    handler.onEditCancelled(this);
  }
}
