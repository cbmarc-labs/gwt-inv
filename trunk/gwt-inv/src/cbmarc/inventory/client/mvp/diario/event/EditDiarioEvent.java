package cbmarc.inventory.client.mvp.diario.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditDiarioEvent extends GwtEvent<EditDiarioEventHandler> {
  public static Type<EditDiarioEventHandler> TYPE = 
	  new Type<EditDiarioEventHandler>();
  private final Long id;
  
  public EditDiarioEvent(Long id) {
	  this.id = id;
  }
  
  public Long getId() { return id; }
  
  @Override
  public Type<EditDiarioEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditDiarioEventHandler handler) {
    handler.onEdit(this);
  }
}
