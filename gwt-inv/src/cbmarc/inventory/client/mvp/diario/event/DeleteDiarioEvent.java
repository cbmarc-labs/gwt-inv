package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteDiarioEvent extends GwtEvent<DeleteDiarioEventHandler> {
  public static Type<DeleteDiarioEventHandler> TYPE = 
	  new Type<DeleteDiarioEventHandler>();
  
  @Override
  public Type<DeleteDiarioEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DeleteDiarioEventHandler handler) {
    handler.onDelete(this);
  }
}
