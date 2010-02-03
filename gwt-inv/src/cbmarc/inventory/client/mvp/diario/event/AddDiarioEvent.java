package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddDiarioEvent extends GwtEvent<AddDiarioEventHandler> {
  public static Type<AddDiarioEventHandler> TYPE = 
	  new Type<AddDiarioEventHandler>();
  
  @Override
  public Type<AddDiarioEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddDiarioEventHandler handler) {
    handler.onAdd(this);
  }
}
