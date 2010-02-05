package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddDeviceEventHandler extends EventHandler {
  void onAdd(AddDeviceEvent event);
}
