package cbmarc.inventory.client.mvp.parte.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddPartesEventHandler extends EventHandler {
  void onAddParte(AddPartesEvent event);
}
