package cbmarc.inventory.client.mvp.diarioparte.event;

import com.google.gwt.event.shared.EventHandler;

public interface SavedDiarioParteEventHandler extends EventHandler {
  void onSaved(SavedDiarioParteEvent event);
}
