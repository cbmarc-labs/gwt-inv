package cbmarc.inventory.client.mvp.contact.event;

import com.google.gwt.event.shared.EventHandler;

public interface SavedContactEventHandler extends EventHandler {
  void onSavedContact(SavedContactEvent event);
}
