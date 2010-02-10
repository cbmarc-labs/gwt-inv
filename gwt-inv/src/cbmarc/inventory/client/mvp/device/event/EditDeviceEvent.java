package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditDeviceEvent extends GwtEvent<EditDeviceEventHandler> {
  public static Type<EditDeviceEventHandler> TYPE = 
	  new Type<EditDeviceEventHandler>();
  private final String key;
  
  public EditDeviceEvent(String key) {
	  this.key = key;
  }
  
  public String getKey() { return key; }
  
  @Override
  public Type<EditDeviceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditDeviceEventHandler handler) {
    handler.onEdit(this);
  }
}
