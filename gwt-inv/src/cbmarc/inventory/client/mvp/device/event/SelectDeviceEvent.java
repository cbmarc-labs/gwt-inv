package cbmarc.inventory.client.mvp.device.event;

import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.event.shared.GwtEvent;

public class SelectDeviceEvent extends GwtEvent<SelectDeviceEventHandler> {
  public static Type<SelectDeviceEventHandler> TYPE = 
	  new Type<SelectDeviceEventHandler>();
  private final Device bean;
  
  public SelectDeviceEvent(Device bean) {
	  this.bean = bean;
  }
  
  public Device getBean() { return bean; }
  
  @Override
  public Type<SelectDeviceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SelectDeviceEventHandler handler) {
    handler.onSelect(this);
  }
}
