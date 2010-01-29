package cbmarc.inventory.client.mvp.diarioparte.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddDiarioParteEvent extends GwtEvent<AddDiarioParteEventHandler> {
  public static Type<AddDiarioParteEventHandler> TYPE = 
	  new Type<AddDiarioParteEventHandler>();
  
  @Override
  public Type<AddDiarioParteEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddDiarioParteEventHandler handler) {
    handler.onAdd(this);
  }
}
