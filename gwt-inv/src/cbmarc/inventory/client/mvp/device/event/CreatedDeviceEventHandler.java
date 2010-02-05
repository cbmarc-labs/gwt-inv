package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface CreatedDeviceEventHandler extends EventHandler {
  void onCreated(CreatedDeviceEvent event);
}
