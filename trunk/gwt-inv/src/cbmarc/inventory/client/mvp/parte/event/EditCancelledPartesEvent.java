package cbmarc.inventory.client.mvp.parte.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditCancelledPartesEvent extends GwtEvent<EditCancelledPartesEventHandler>{
  public static Type<EditCancelledPartesEventHandler> TYPE = new Type<EditCancelledPartesEventHandler>();
  
  @Override
  public Type<EditCancelledPartesEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditCancelledPartesEventHandler handler) {
    handler.onEditCancelledParte(this);
  }
}
