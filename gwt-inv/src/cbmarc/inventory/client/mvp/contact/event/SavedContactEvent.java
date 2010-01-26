package cbmarc.inventory.client.mvp.contact.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavedContactEvent extends GwtEvent<SavedContactEventHandler> {
  public static Type<SavedContactEventHandler> TYPE = 
	  new Type<SavedContactEventHandler>();
  
  @Override
  public Type<SavedContactEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SavedContactEventHandler handler) {
    handler.onSavedContact(this);
  }
}
