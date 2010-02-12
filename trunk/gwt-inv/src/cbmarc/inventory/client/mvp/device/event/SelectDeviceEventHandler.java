package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface SelectDeviceEventHandler extends EventHandler {
  void onSelect(SelectDeviceEvent event);
}
