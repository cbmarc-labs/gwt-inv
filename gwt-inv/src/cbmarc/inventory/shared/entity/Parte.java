/**
 * 
 */
package cbmarc.inventory.shared.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author MCOSTA
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Parte implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Identificador automatico del registro
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	// fecha de la creacion del registro en la DDBB
	@Persistent
	private Date date;
	
	// Nombre del asistente
	@Persistent
	private String atu;
	
	// Fecha del parte
	@Persistent
	private Date fecha;
	
	// Numero asignado al parte
	@Persistent
	private String numero;
		
	/**
	 * 
	 */
	public Parte() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * @return the atu
	 */
	public String getAtu() {
		return atu;
	}

	/**
	 * @param atu the atu to set
	 */
	public void setAtu(final String atu) {
		this.atu = atu;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the numparte
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numparte the numparte to set
	 */
	public void setNumero(final String numero) {
		this.numero = numero;
	}
}
