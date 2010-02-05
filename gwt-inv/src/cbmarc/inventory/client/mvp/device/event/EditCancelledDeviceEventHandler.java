package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditCancelledDeviceEventHandler extends EventHandler {
  void onEditCancelled(EditCancelledDeviceEvent event);
}
