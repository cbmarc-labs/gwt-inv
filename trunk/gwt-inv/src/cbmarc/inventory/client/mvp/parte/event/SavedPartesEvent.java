package cbmarc.inventory.client.mvp.parte.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavedPartesEvent extends GwtEvent<SavedPartesEventHandler> {
  public static Type<SavedPartesEventHandler> TYPE = 
	  new Type<SavedPartesEventHandler>();
  
  @Override
  public Type<SavedPartesEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SavedPartesEventHandler handler) {
    handler.onSavedParte(this);
  }
}
