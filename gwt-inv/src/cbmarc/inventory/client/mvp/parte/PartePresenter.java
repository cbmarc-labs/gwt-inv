/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.parte.event.AddPartesEvent;
import cbmarc.inventory.client.mvp.parte.event.AddPartesEventHandler;
import cbmarc.inventory.client.mvp.parte.event.EditCancelledPartesEvent;
import cbmarc.inventory.client.mvp.parte.event.EditCancelledPartesEventHandler;
import cbmarc.inventory.client.mvp.parte.event.EditPartesEvent;
import cbmarc.inventory.client.mvp.parte.event.EditPartesEventHandler;
import cbmarc.inventory.client.mvp.parte.event.SavedPartesEvent;
import cbmarc.inventory.client.mvp.parte.event.SavedPartesEventHandler;
import cbmarc.inventory.shared.entity.Parte;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class PartePresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final ParteServiceAsync rpcService; 
	private final HandlerManager eventBus;
	private final Display display;
	
	private final ListPartePresenter list;
	private final EditPartePresenter edit;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public PartePresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    this.rpcService = GWT.create(ParteService.class);
	    
		this.list = new ListPartePresenter(
				rpcService, eventBus, new ListParteView());
		this.edit = new EditPartePresenter(
				rpcService, eventBus, new EditParteView());
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {		
		eventBus.addHandler(AddPartesEvent.TYPE,
				new AddPartesEventHandler() {
			public void onAddParte(AddPartesEvent event) {
				doAddNewParte();
			}
	    });
		
		eventBus.addHandler(EditCancelledPartesEvent.TYPE,
				new EditCancelledPartesEventHandler() {

			@Override
			public void onEditCancelledParte(EditCancelledPartesEvent event) {
				doEditParteCancelled();
			}
			
	    });
		
		eventBus.addHandler(SavedPartesEvent.TYPE, 
				new SavedPartesEventHandler() {

			@Override
			public void onSavedParte(SavedPartesEvent event) {
				doEditParteCancelled();
			}
			
		});
		
		eventBus.addHandler(EditPartesEvent.TYPE, 
				new EditPartesEventHandler() {

			@Override
			public void onEditParte(EditPartesEvent event) {
				doEditParte(event.getId());
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doAddNewParte() {
		edit.setParte(new Parte());
		edit.go(display.getContent());
	}
	
	/**
	 * 
	 */
	private void doEditParteCancelled() {
		list.go(display.getContent());
	}
	
	/**
	 * @param key
	 */
	private void doEditParte(Long id) {
		rpcService.select(id, new AsyncCallback<Parte>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("NO SE HA PODIDO");
			}

			@Override
			public void onSuccess(Parte result) {
				if(result != null) {
					edit.setParte(result);
					edit.go(display.getContent());
				} else {
					Window.alert("NO SE HA PODIDO");
				}
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		list.go(display.getContent());
	    container.add(display.asWidget());
	}

}
