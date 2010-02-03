package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditDiarioEventHandler extends EventHandler {
  void onEdit(EditDiarioEvent event);
}
