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
public class Diario implements Serializable {
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
	
	// Fecha del diario del parte
	@Persistent
	private Date fecha;
	
	// Hora de la entrada en el diario
	@Persistent
	private Date hora;
	
	// accion del tecnico
	@Persistent
	private String accion;
	
	// foreign key to parte
	@Persistent
	private Long parte;
		
	/**
	 * 
	 */
	public Diario() {
	}
	
	/**
	 * 
	 */
	public Diario(final Date fecha, final Date hora, final String accion) {
		this.fecha = fecha;
		this.hora = hora;
		this.accion = accion;
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
	 * @return the hora
	 */
	public Date getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(final Date hora) {
		this.hora = hora;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(final String accion) {
		this.accion = accion;
	}

	/**
	 * @return the parte
	 */
	public Long getParte() {
		return parte;
	}

	/**
	 * @param parte the parte to set
	 */
	public void setParte(Long parte) {
		this.parte = parte;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[DiarioParte");
		//sb.append("[id: " + this.id.toString() + "]");
		//sb.append("[fecha: " + this.fecha.toString() + "]");
		//sb.append("[hora: " + this.hora.toString() + "]");
		sb.append("[accion: " + this.accion + "]");
		sb.append("]");
		
		return sb.toString();
	}
}
