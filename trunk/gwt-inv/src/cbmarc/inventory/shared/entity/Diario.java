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
	public Long id;
	
	// fecha de la creacion del registro en la DDBB
	@Persistent
	private Date date;
	
	// Identificador del parte
	@Persistent
	public Long par_id;
	
	// Fecha del diario del parte
	@Persistent
	public Date fecha = new Date();
	
	// Hora de la entrada en el diario
	@Persistent
	public Date hora = new Date();
	
	// accion del tecnico
	@Persistent
	public String accion = "";
		
	/**
	 * 
	 */
	public Diario() {
	}
	
	/**
	 * 
	 */
	public Diario(Date fecha, Date hora, String accion) {
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the par_id
	 */
	public Long getPar_id() {
		return par_id;
	}

	/**
	 * @param parId the par_id to set
	 */
	public void setPar_id(Long parId) {
		par_id = parId;
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
	public void setFecha(Date fecha) {
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
	public void setHora(Date hora) {
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
	public void setAccion(String accion) {
		this.accion = accion;
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
