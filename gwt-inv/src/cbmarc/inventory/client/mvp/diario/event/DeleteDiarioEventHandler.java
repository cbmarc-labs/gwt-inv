package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteDiarioEventHandler extends EventHandler {
  void onDelete(DeleteDiarioEvent event);
}
