package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddDiarioEventHandler extends EventHandler {
  void onAdd(AddDiarioEvent event);
}
