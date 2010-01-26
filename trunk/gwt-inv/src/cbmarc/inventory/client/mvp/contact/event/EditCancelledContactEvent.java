package cbmarc.inventory.client.mvp.contact.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditCancelledContactEvent extends GwtEvent<EditCancelledContactEventHandler>{
  public static Type<EditCancelledContactEventHandler> TYPE = new Type<EditCancelledContactEventHandler>();
  
  @Override
  public Type<EditCancelledContactEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditCancelledContactEventHandler handler) {
    handler.onEditContactCancelled(this);
  }
}
