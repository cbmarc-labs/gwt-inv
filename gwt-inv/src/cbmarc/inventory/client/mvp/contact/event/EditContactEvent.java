package cbmarc.inventory.client.mvp.contact.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditContactEvent extends GwtEvent<EditContactEventHandler> {
  public static Type<EditContactEventHandler> TYPE = 
	  new Type<EditContactEventHandler>();
  private final Long id;
  
  public EditContactEvent(Long id) {
	  this.id = id;
  }
  
  public Long getId() { return id; }
  
  @Override
  public Type<EditContactEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditContactEventHandler handler) {
    handler.onEditContact(this);
  }
}
