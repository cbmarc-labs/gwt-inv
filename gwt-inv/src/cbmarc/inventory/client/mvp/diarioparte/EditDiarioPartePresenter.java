/**
 * 
 */
package cbmarc.inventory.client.mvp.diarioparte;

import java.util.Date;

import cbmarc.inventory.client.mvp.Presenter;
import cbmarc.inventory.client.mvp.diarioparte.event.EditCancelledDiarioParteEvent;
import cbmarc.inventory.client.mvp.diarioparte.event.SavedDiarioParteEvent;
import cbmarc.inventory.shared.entity.DiarioParte;

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
public class EditDiarioPartePresenter implements Presenter {
	
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
	
	private DiarioParte diarioParte;
	
	private final DiarioParteServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	/**
	 * @param rpcService
	 * @param eventBus
	 * @param view
	 */
	public EditDiarioPartePresenter(DiarioParteServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    this.diarioParte = new DiarioParte();
	    
	    bind();
	}
	
	/**
	 * 
	 */
	public void bind() {
		display.getListButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledDiarioParteEvent());
			}
	    	
	    });
		
		display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditCancelledDiarioParteEvent());
			}
			
		});
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSave();
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doSave() {
		diarioParte.setFecha(display.getFecha().getValue());
		diarioParte.setHora(display.getHora());
		diarioParte.setAccion(display.getAccion().getValue());
		
		rpcService.save(diarioParte, new AsyncCallback<DiarioParte>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save failed: " + caught.toString());
			}

			@Override
			public void onSuccess(DiarioParte result) {
				eventBus.fireEvent(new SavedDiarioParteEvent());
			}
			
		});
	}

	/**
	 * @return the diarioParte
	 */
	public DiarioParte getDiarioParte() {
		return diarioParte;
	}

	/**
	 * @param diarioParte the diarioParte to set
	 */
	public void setDiarioParte(DiarioParte diarioParte) {
		this.diarioParte = diarioParte;
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		
		display.reset();
		
		display.setFecha(this.diarioParte.getFecha());
		display.setHora(this.diarioParte.getHora());
		display.getAccion().setValue(this.diarioParte.getAccion());
		
	    container.add(display.asWidget());
	    display.getAccion().setFocus(true);
	}

}
