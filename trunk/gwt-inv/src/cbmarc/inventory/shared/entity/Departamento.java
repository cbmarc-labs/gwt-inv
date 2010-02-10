/**
 * 
 */
package cbmarc.inventory.shared.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
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
public class Departamento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Identificador del registro
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String encodedKey;
	
	// fecha de la creacion
	@Persistent
	private Date date;
	
	// nombre
	@Persistent
	private String nombre;
	
	// observaciones
	@Persistent
	private String observaciones;
	
	/**
	 * 
	 */
	public Departamento() {
	}
	
	public Departamento(String nombre, String observaciones) {
		this.nombre = nombre;
		this.observaciones = observaciones;
	}

	/**
	 * @return the encodedKey
	 */
	public final String getEncodedKey() {
		return encodedKey;
	}

	/**
	 * @return the date
	 */
	public final Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public final void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the nombre
	 */
	public final String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the observaciones
	 */
	public final String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public final void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[Departamento");
		sb.append("[id: " + this.encodedKey + "]");
		sb.append("[fecha: " + this.date.toString() + "]");
		sb.append("[nombre: " + this.nombre + "]");
		sb.append("[observaciones: " + this.observaciones + "]");
		sb.append("]");
		
		return sb.toString();
	}
}
