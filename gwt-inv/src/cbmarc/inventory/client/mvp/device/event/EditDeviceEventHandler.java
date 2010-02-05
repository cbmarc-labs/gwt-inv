package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditDeviceEventHandler extends EventHandler {
  void onEdit(EditDeviceEvent event);
}
