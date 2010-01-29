/**
 * 
 */
package cbmarc.inventory.client.mvp.diarioparte;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.diarioparte.event.AddDiarioParteEvent;
import cbmarc.inventory.client.mvp.diarioparte.event.AddDiarioParteEventHandler;
import cbmarc.inventory.client.mvp.diarioparte.event.EditCancelledDiarioParteEvent;
import cbmarc.inventory.client.mvp.diarioparte.event.EditCancelledDiarioParteEventHandler;
import cbmarc.inventory.client.mvp.diarioparte.event.EditDiarioParteEvent;
import cbmarc.inventory.client.mvp.diarioparte.event.EditDiarioParteEventHandler;
import cbmarc.inventory.client.mvp.diarioparte.event.SavedDiarioParteEvent;
import cbmarc.inventory.client.mvp.diarioparte.event.SavedDiarioParteEventHandler;
import cbmarc.inventory.shared.entity.DiarioParte;

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
public class DiarioPartePresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final DiarioParteServiceAsync rpcService; 
	private final HandlerManager eventBus;
	private final Display display;
	
	private final ListDiarioPartePresenter list;
	private final EditDiarioPartePresenter edit;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public DiarioPartePresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    this.rpcService = GWT.create(DiarioParteService.class);
	    
		this.list = new ListDiarioPartePresenter(
				rpcService, eventBus, new ListDiarioParteView());
		this.edit = new EditDiarioPartePresenter(
				rpcService, eventBus, new EditDiarioParteView());
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {		
		eventBus.addHandler(AddDiarioParteEvent.TYPE,
				new AddDiarioParteEventHandler() {
			public void onAdd(AddDiarioParteEvent event) {
				doAdd();
			}
	    });
		
		eventBus.addHandler(EditCancelledDiarioParteEvent.TYPE,
				new EditCancelledDiarioParteEventHandler() {

			@Override
			public void onEditCancelled(EditCancelledDiarioParteEvent event) {
				doEditCancelled();
			}
			
	    });
		
		eventBus.addHandler(SavedDiarioParteEvent.TYPE, 
				new SavedDiarioParteEventHandler() {

			@Override
			public void onSaved(SavedDiarioParteEvent event) {
				doEditCancelled();
			}
			
		});
		
		eventBus.addHandler(EditDiarioParteEvent.TYPE, 
				new EditDiarioParteEventHandler() {

			@Override
			public void onEdit(EditDiarioParteEvent event) {
				doEdit(event.getId());
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doAdd() {
		edit.setDiarioParte(new DiarioParte());
		edit.go(display.getContent());
	}
	
	/**
	 * 
	 */
	private void doEditCancelled() {
		list.go(display.getContent());
	}
	
	/**
	 * @param id
	 */
	private void doEdit(Long id) {
		rpcService.select(id, new AsyncCallback<DiarioParte>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("NO SE HA PODIDO");
			}

			@Override
			public void onSuccess(DiarioParte result) {
				if(result != null) {
					edit.setDiarioParte(result);
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
