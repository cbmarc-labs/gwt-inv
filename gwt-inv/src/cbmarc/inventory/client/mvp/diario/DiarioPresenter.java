/**
 * 
 */
package cbmarc.inventory.client.mvp.diario;

import java.util.Date;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.diario.event.AddDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.AddDiarioEventHandler;
import cbmarc.inventory.client.mvp.diario.event.CreatedDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.CreatedDiarioEventHandler;
import cbmarc.inventory.client.mvp.diario.event.DeleteDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.DeleteDiarioEventHandler;
import cbmarc.inventory.client.mvp.diario.event.EditCancelledDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.EditCancelledDiarioEventHandler;
import cbmarc.inventory.client.mvp.diario.event.EditDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.EditDiarioEventHandler;
import cbmarc.inventory.client.mvp.diario.event.SaveDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.SaveDiarioEventHandler;
import cbmarc.inventory.shared.entity.Diario;

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
public class DiarioPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private HandlerManager diarioEventBus = new HandlerManager(null);
	
	private final DiarioServiceAsync rpcService;
	private final HandlerManager eventBus;
	protected final Display display;
	
	protected final ListDiarioPresenter list;
	protected final EditDiarioPresenter edit;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public DiarioPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    this.rpcService = GWT.create(DiarioService.class);
	    
		this.list = new ListDiarioPresenter(
				rpcService, diarioEventBus, new ListDiarioView());
		this.edit = new EditDiarioPresenter(
				rpcService, diarioEventBus, new EditDiarioView());
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {		
		diarioEventBus.addHandler(AddDiarioEvent.TYPE,
				new AddDiarioEventHandler() {
			public void onAdd(AddDiarioEvent event) {
				doAdd();
			}
	    });
		
		diarioEventBus.addHandler(EditCancelledDiarioEvent.TYPE,
				new EditCancelledDiarioEventHandler() {

			@Override
			public void onEditCancelled(EditCancelledDiarioEvent event) {
				list.go(display.getContent());
			}
			
	    });
		
		diarioEventBus.addHandler(CreatedDiarioEvent.TYPE, 
				new CreatedDiarioEventHandler() {

			@Override
			public void onCreated(CreatedDiarioEvent event) {
				list.go(display.getContent());
			}
			
		});
		
		diarioEventBus.addHandler(EditDiarioEvent.TYPE, 
				new EditDiarioEventHandler() {

			@Override
			public void onEdit(EditDiarioEvent event) {
				doEdit(event.getId());
			}
			
		});
		
		diarioEventBus.addHandler(DeleteDiarioEvent.TYPE, 
				new DeleteDiarioEventHandler() {

			@Override
			public void onDelete(DeleteDiarioEvent event) {
				list.deleteSelected();
			}
			
		});
		
		diarioEventBus.addHandler(SaveDiarioEvent.TYPE, 
				new SaveDiarioEventHandler() {

			@Override
			public void onSave(SaveDiarioEvent event) {
				doSave();
			}
			
		});
	}
	
	/**
	 * 
	 */
	protected void doAdd() {
		Diario diario = new Diario();
		diario.setFecha(new Date());
		diario.setHora(new Date());
		
		edit.setDiario(diario);
		edit.go(display.getContent());
	}
	
	/**
	 * 
	 */
	protected void doSave() {
		edit.doSave();
	}
	
	/**
	 * @param id
	 */
	private void doEdit(Long id) {
		rpcService.selectById(id, new AsyncCallback<Diario>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("NO SE HA PODIDO");
			}

			@Override
			public void onSuccess(Diario result) {
				if(result != null) {
					edit.setDiario(result);
					edit.go(display.getContent());
				} else {
					Window.alert("NO SE HA PODIDO");
				}
			}
			
		});
	}

	/**
	 * @return the list
	 */
	public ListDiarioPresenter getList() {
		return list;
	}

	/**
	 * @return the edit
	 */
	public EditDiarioPresenter getEdit() {
		return edit;
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
