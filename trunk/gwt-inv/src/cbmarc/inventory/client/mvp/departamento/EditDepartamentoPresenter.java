/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.departamento.event.CreatedDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.EditCancelledDepartamentoEvent;
import cbmarc.inventory.client.mvp.departamento.event.SaveDepartamentoEvent;
import cbmarc.inventory.shared.entity.Departamento;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class EditDepartamentoPresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getListButton();
		
		TextBox getNombre();
		HasValue<String> getObservaciones();
		
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		
		void reset();
		
		Widget asWidget();
	}
	
	private final DepartamentoServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private Departamento bean = new Departamento();
	
	/**
	 * @param rpcService
	 * @param eventBus
	 * @param view
	 */
	public EditDepartamentoPresenter(DepartamentoServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
		display.getListButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledDepartamentoEvent());
			}
	    	
	    });
		
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledDepartamentoEvent());
			}
			
		});
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new SaveDepartamentoEvent());
			}
			
		});
	}
	
	/**
	 * @return
	 */
	public Departamento getBean() {
		return bean;
	}

	/**
	 * @param bean
	 */
	public void setBean(Departamento bean) {
		this.bean = bean;
	}

	public boolean doSave() {
		this.bean.setNombre(display.getNombre().getValue());
		this.bean.setObservaciones(display.getObservaciones().getValue());
		
		rpcService.save(bean, new AsyncCallback<Departamento>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(Departamento result) {
				eventBus.fireEvent(new CreatedDepartamentoEvent());
			}
			
		});
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();

		display.reset();
		if(bean.getEncodedKey() != null) {
			display.getNombre().setValue(bean.getNombre());
			display.getObservaciones().setValue(bean.getObservaciones());
		}
		
	    container.add(display.asWidget());
	    display.getNombre().setFocus(true);
	}

}
