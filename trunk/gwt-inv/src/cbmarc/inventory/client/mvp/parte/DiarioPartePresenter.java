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
	
	private Long numParte = null;

	public DiarioPartePresenter(HandlerManager eventBus, Display view) {
		super(eventBus, view);
	}

	/**
	 * @return the numParte
	 */
	public Long getNumParte() {
		return numParte;
	}

	/**
	 * @param numParte the numParte to set
	 */
	public void setNumParte(Long numParte) {
		this.numParte = numParte;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diario.DiarioPresenter#doAdd()
	 */
	@Override
	protected void doAdd() {
		Diario diario = new Diario();
		diario.setPar_id(this.numParte);
		diario.setFecha(new Date());
		diario.setHora(new Date());
		
		this.edit.setDiario(diario);
		edit.go(display.getContent());
	}

}
