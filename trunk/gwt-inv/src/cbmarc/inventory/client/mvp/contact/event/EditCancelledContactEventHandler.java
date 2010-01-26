package cbmarc.inventory.client.mvp.contact.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditCancelledContactEventHandler extends EventHandler {
  void onEditContactCancelled(EditCancelledContactEvent event);
}
