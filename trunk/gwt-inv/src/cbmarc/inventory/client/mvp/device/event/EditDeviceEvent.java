package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditDeviceEvent extends GwtEvent<EditDeviceEventHandler> {
  public static Type<EditDeviceEventHandler> TYPE = 
	  new Type<EditDeviceEventHandler>();
  private final Long id;
  
  public EditDeviceEvent(Long id) {
	  this.id = id;
  }
  
  public Long getId() { return id; }
  
  @Override
  public Type<EditDeviceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditDeviceEventHandler handler) {
    handler.onEdit(this);
  }
}
