package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class SelectCancelledDeviceEvent 
		extends GwtEvent<SelectCancelledDeviceEventHandler> {
  public static Type<SelectCancelledDeviceEventHandler> TYPE = 
	  new Type<SelectCancelledDeviceEventHandler>();
  
  @Override
  public Type<SelectCancelledDeviceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SelectCancelledDeviceEventHandler handler) {
    handler.onSelectCancelled(this);
  }
}
