package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface SaveDeviceEventHandler extends EventHandler {
  void onSave(SaveDeviceEvent event);
}
