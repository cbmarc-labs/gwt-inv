package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.EventHandler;

public interface SaveDiarioEventHandler extends EventHandler {
  void onSave(SaveDiarioEvent event);
}
