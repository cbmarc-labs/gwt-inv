package cbmarc.inventory.client.mvp.diarioparte.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditCancelledDiarioParteEvent 
		extends GwtEvent<EditCancelledDiarioParteEventHandler> {
  public static Type<EditCancelledDiarioParteEventHandler> TYPE = 
	  new Type<EditCancelledDiarioParteEventHandler>();
  
  @Override
  public Type<EditCancelledDiarioParteEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditCancelledDiarioParteEventHandler handler) {
    handler.onEditCancelled(this);
  }
}
