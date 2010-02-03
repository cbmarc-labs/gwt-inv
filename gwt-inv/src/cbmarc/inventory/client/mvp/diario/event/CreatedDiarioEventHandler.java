package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.EventHandler;

public interface CreatedDiarioEventHandler extends EventHandler {
  void onCreated(CreatedDiarioEvent event);
}
