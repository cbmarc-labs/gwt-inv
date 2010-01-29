package cbmarc.inventory.client.mvp.parte.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddPartesEvent extends GwtEvent<AddPartesEventHandler> {
  public static Type<AddPartesEventHandler> TYPE = 
	  new Type<AddPartesEventHandler>();
  
  @Override
  public Type<AddPartesEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddPartesEventHandler handler) {
    handler.onAddParte(this);
  }
}
