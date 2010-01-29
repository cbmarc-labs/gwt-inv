package cbmarc.inventory.client.mvp.parte.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditPartesEvent extends GwtEvent<EditPartesEventHandler> {
  public static Type<EditPartesEventHandler> TYPE = 
	  new Type<EditPartesEventHandler>();
  private final Long id;
  
  public EditPartesEvent(Long id) {
	  this.id = id;
  }
  
  public Long getId() { return id; }
  
  @Override
  public Type<EditPartesEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditPartesEventHandler handler) {
    handler.onEditParte(this);
  }
}
