/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;

import java.util.Date;

import cbmarc.inventory.client.mvp.diario.DiarioPresenter;
import cbmarc.inventory.shared.entity.Diario;

import com.google.gwt.event.shared.HandlerManager;

/**
 * @author MCOSTA
 *
 */
public class DiarioPartePresenter extends DiarioPresenter {
	
	private Long parte = null;

	public DiarioPartePresenter(HandlerManager eventBus, Display view) {
		super(eventBus, view);
	}

	/**
	 * @return the numParte
	 */
	public Long getParte() {
		return parte;
	}
	
	/**
	 * @param numParte the numParte to set
	 */
	public void setParte(Long parte) {
		this.parte = parte;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diario.DiarioPresenter#doAdd()
	 */
	@Override
	protected void doAdd() {
		Diario diario = new Diario();
		diario.setFecha(new Date());
		diario.setHora(new Date());
		diario.setParte(this.parte);
		
		this.edit.setDiario(diario);
		edit.go(display.getContent());
	}

}
