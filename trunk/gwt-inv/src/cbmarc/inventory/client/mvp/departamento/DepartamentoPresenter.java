/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.departamento.event.AddDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.AddDepartamentoEventHandler;
import cbmarc.inventory.client.mvp.departamento.event.CreatedDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.CreatedDepartamentoEventHandler;
import cbmarc.inventory.client.mvp.departamento.event.DeleteDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.DeleteDepartamentoEventHandler;
import cbmarc.inventory.client.mvp.departamento.event.EditCancelledDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.EditCancelledDepartamentoEventHandler;
import cbmarc.inventory.client.mvp.departamento.event.EditDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.EditDepartamentoEventHandler;
import cbmarc.inventory.client.mvp.departamento.event.SaveDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.SaveDepartamentoEventHandler;
import cbmarc.inventory.shared.entity.Departamento;

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
public class DepartamentoPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final DepartamentoServiceAsync rpcService;
	private final HandlerManager eventBus;
	protected final Display display;
	
	protected final ListDepartamentoPresenter list;
	protected final EditDepartamentoPresenter edit;
	
	/**
	 * @param eventBus
	 * @param view
	 */
	public DepartamentoPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    this.rpcService = GWT.create(DepartamentoService.class);
	    
		this.list = new ListDepartamentoPresenter(
				rpcService, eventBus, new ListDepartamentoView());
		this.edit = new EditDepartamentoPresenter(
				rpcService, eventBus, new EditDepartamentoView());
		
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {		
		eventBus.addHandler(AddDepartamentoEvent.TYPE,
				new AddDepartamentoEventHandler() {
			public void onAdd(AddDepartamentoEvent event) {
				doAdd();
			}
	    });
		
		eventBus.addHandler(EditCancelledDepartamentoEvent.TYPE,
				new EditCancelledDepartamentoEventHandler() {

			@Override
			public void onEditCancelled(EditCancelledDepartamentoEvent event) {
				list.go(display.getContent());
			}
			
	    });
		
		eventBus.addHandler(CreatedDepartamentoEvent.TYPE, 
				new CreatedDepartamentoEventHandler() {

			@Override
			public void onCreated(CreatedDepartamentoEvent event) {
				list.go(display.getContent());
			}
			
		});
		
		eventBus.addHandler(EditDepartamentoEvent.TYPE, 
				new EditDepartamentoEventHandler() {

			@Override
			public void onEdit(EditDepartamentoEvent event) {
				doEdit(event.getEncodedKey());
			}
			
		});
		
		eventBus.addHandler(DeleteDepartamentoEvent.TYPE, 
				new DeleteDepartamentoEventHandler() {

			@Override
			public void onDelete(DeleteDepartamentoEvent event) {
				list.deleteSelected();
			}
			
		});
		
		eventBus.addHandler(SaveDepartamentoEvent.TYPE, 
				new SaveDepartamentoEventHandler() {

			@Override
			public void onSave(SaveDepartamentoEvent event) {
				edit.doSave();
			}
			
		});
	}
	
	/**
	 * 
	 */
	protected void doAdd() {
		edit.setBean(new Departamento());
		edit.go(display.getContent());
	}
	
	/**
	 * @param id
	 */
	private void doEdit(String encodedKey) {
		rpcService.selectByKey(encodedKey, new AsyncCallback<Departamento>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("NO SE HA PODIDO");
			}

			@Override
			public void onSuccess(Departamento bean) {
				if(bean != null) {
					edit.setBean(bean);
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
	public ListDepartamentoPresenter getList() {
		return list;
	}

	/**
	 * @return the edit
	 */
	public EditDepartamentoPresenter getEdit() {
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
