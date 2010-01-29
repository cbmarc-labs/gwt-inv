package cbmarc.inventory.client.mvp.parte.event;

import com.google.gwt.event.shared.EventHandler;

public interface SavedPartesEventHandler extends EventHandler {
  void onSavedParte(SavedPartesEvent event);
}
