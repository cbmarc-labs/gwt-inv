package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteDeviceEventHandler extends EventHandler {
  void onDelete(DeleteDeviceEvent event);
}
