package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditCancelledDiarioEventHandler extends EventHandler {
  void onEditCancelled(EditCancelledDiarioEvent event);
}
