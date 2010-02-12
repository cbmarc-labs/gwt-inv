package cbmarc.inventory.client.mvp.device.event;

import com.google.gwt.event.shared.EventHandler;

public interface SelectCancelledDeviceEventHandler extends EventHandler {
  void onSelectCancelled(SelectCancelledDeviceEvent event);
}
