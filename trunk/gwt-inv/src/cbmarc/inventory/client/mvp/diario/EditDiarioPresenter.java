/**
 * 
 */
package cbmarc.inventory.client.mvp.diario;

import java.util.Date;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.diario.event.EditCancelledDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.CreatedDiarioEvent;
import cbmarc.inventory.client.mvp.diario.event.SaveDiarioEvent;
import cbmarc.inventory.shared.entity.Diario;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * @author MCOSTA
 *
 */
public class EditDiarioPresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getListButton();
		
		void setFecha(Date fecha);
		DateBox getFecha();
	    Date getHora();
	    void setHora(Date date);
	    TextArea getAccion();
		
		HasClickHandlers getSubmitButton();
		HasClickHandlers getCancelButton();
		
		void reset();
		
		Widget asWidget();
	}
	
	private final DiarioServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private Diario diario = null;
	
	/**
	 * @param rpcService
	 * @param eventBus
	 * @param view
	 */
	public EditDiarioPresenter(DiarioServiceAsync rpcService, 
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
				eventBus.fireEvent(new EditCancelledDiarioEvent());
			}
	    	
	    });
		
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledDiarioEvent());
			}
			
		});
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new SaveDiarioEvent());
			}
			
		});
	}
	
	/**
	 * @return the diario
	 */
	public Diario getDiario() {
		return diario;
	}

	/**
	 * @param diario the diario to set
	 */
	public void setDiario(Diario diario) {
		this.diario = diario;
	}

	/**
	 * 
	 */
	public boolean doSave() {
		if(diario == null) return false;
		
		diario.setFecha(display.getFecha().getValue());
		diario.setHora(display.getHora());
		diario.setAccion(display.getAccion().getValue());
		
		rpcService.save(diario, new AsyncCallback<Diario>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(Diario result) {
				eventBus.fireEvent(new CreatedDiarioEvent());
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
		
		if(diario != null) {
			display.setFecha(diario.getFecha());
			display.setHora(diario.getHora());
			display.getAccion().setValue(diario.getAccion());
		}
		
	    container.add(display.asWidget());
	    display.getAccion().setFocus(true);
	}

}
