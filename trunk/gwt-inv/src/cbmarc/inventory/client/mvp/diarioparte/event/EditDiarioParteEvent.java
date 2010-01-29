package cbmarc.inventory.client.mvp.diarioparte.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditDiarioParteEvent extends GwtEvent<EditDiarioParteEventHandler> {
  public static Type<EditDiarioParteEventHandler> TYPE = 
	  new Type<EditDiarioParteEventHandler>();
  private final Long id;
  
  public EditDiarioParteEvent(Long id) {
	  this.id = id;
  }
  
  public Long getId() { return id; }
  
  @Override
  public Type<EditDiarioParteEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditDiarioParteEventHandler handler) {
    handler.onEdit(this);
  }
}
