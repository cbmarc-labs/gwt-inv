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
public class Device implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Identificador automatico del registro
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String encodedKey;
	
	// fecha de la creacion del registro en la DDBB
	@Persistent
	private Date date;
	
	// serial number
	@Persistent
	private String sn;
	
	// name
	@Persistent
	private String nombre;
	
	// marca
	@Persistent
	private String marca;
	
	//modelo
	@Persistent
	private String modelo;
	
	// tipo
	@Persistent
	private String tipo;
	
	// centro
	@Persistent
	private String centro;
	
	// sociedad
	@Persistent
	private String sociedad;
	
	@Persistent
	private Departamento departamento;
	
	@Persistent
	private String ubicacion;
	
	@Persistent
	private Date fechaCompra;
	
	@Persistent
	private Date fechaFinGarantia;
	
	@Persistent
	private String proveedor;
	
	@Persistent
	private Boolean mantenimiento;
	
	@Persistent
	private String observaciones;
		
	/**
	 * 
	 */
	public Device() {
	}

	/**
	 * @return the id
	 */
	public String getKey() { //getKeyId() | getEncodedKey() | getKey() | getId()
		return encodedKey;
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
	 * @return the sn
	 */
	public final String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public final void setSn(String sn) {
		this.sn = sn;
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
	 * @return the marca
	 */
	public final String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public final void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public final String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public final void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the tipo
	 */
	public final String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public final void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the centro
	 */
	public final String getCentro() {
		return centro;
	}

	/**
	 * @param centro the centro to set
	 */
	public final void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * @return the sociedad
	 */
	public final String getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public final void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the departamento
	 */
	public final Departamento getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public final void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the ubicacion
	 */
	public final String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public final void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the fechaCompra
	 */
	public final Date getFechaCompra() {
		return fechaCompra;
	}

	/**
	 * @param fechaCompra the fechaCompra to set
	 */
	public final void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	/**
	 * @return the fechaFinGarantia
	 */
	public final Date getFechaFinGarantia() {
		return fechaFinGarantia;
	}

	/**
	 * @param fechaFinGarantia the fechaFinGarantia to set
	 */
	public final void setFechaFinGarantia(Date fechaFinGarantia) {
		this.fechaFinGarantia = fechaFinGarantia;
	}

	/**
	 * @return the proveedor
	 */
	public final String getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public final void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the mantenimiento
	 */
	public final Boolean getMantenimiento() {
		return mantenimiento;
	}

	/**
	 * @param mantenimiento the mantenimiento to set
	 */
	public final void setMantenimiento(Boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
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
		
		sb.append("[Device");
		sb.append("[key: " + this.encodedKey + "]");
		sb.append("[fecha: " + this.date.toString() + "]");
		sb.append("]");
		
		return sb.toString();
	}
}
