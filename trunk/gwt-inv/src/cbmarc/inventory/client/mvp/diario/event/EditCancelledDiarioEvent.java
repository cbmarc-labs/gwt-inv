package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditCancelledDiarioEvent 
		extends GwtEvent<EditCancelledDiarioEventHandler> {
  public static Type<EditCancelledDiarioEventHandler> TYPE = 
	  new Type<EditCancelledDiarioEventHandler>();
  
  @Override
  public Type<EditCancelledDiarioEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditCancelledDiarioEventHandler handler) {
    handler.onEditCancelled(this);
  }
}
