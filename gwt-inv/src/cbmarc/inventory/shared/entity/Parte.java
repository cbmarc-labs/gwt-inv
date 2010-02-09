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
	private String numparte;
	
	// Identificador del dispositivo
	@Persistent
	private String dis_id;
	
	// Numero de serie del dispositivo
	@Persistent
	private String dis_sn;
	
	// Nombre de dispositivo
	@Persistent
	private String dis_nombre;
	
	// Marca del dispositivo
	@Persistent
	private String dis_marca;
	
	// Modelo del dispositivo
	@Persistent
	private String dis_modelo;
	
	// tipo de dispositivo
	@Persistent
	private String dis_tipo;
	
	// Centro del dispositivo
	@Persistent
	private String dis_centro;
	
	// Departamento del dispositivo
	@Persistent
	private String dis_departamento;
	
	// Ubicacion del dispositivo
	@Persistent
	private String dis_ubicacion;
	
	// fecha de compra del dispositivo
	@Persistent
	private String dis_fecha_compra;
	
	// Fin de la garantia
	@Persistent
	private String dis_fin_garantia;
	
	// Proveedor
	@Persistent
	private String dis_proveedor;
	
	// Mantenimiento
	@Persistent
	private String dis_mantenimiento;
	
	// observaciones
	@Persistent
	private String dis_observaciones;
		
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
	public String getNumparte() {
		return numparte;
	}

	/**
	 * @param numparte the numparte to set
	 */
	public void setNumparte(final String numparte) {
		this.numparte = numparte;
	}

	/**
	 * @return the dis_id
	 */
	public String getDis_id() {
		return dis_id;
	}

	/**
	 * @param disId the dis_id to set
	 */
	public void setDis_id(final String disId) {
		dis_id = disId;
	}

	/**
	 * @return the dis_sn
	 */
	public String getDis_sn() {
		return dis_sn;
	}

	/**
	 * @param disSn the dis_sn to set
	 */
	public void setDis_sn(final String disSn) {
		dis_sn = disSn;
	}

	/**
	 * @return the dis_nombre
	 */
	public String getDis_nombre() {
		return dis_nombre;
	}

	/**
	 * @param disNombre the dis_nombre to set
	 */
	public void setDis_nombre(final String disNombre) {
		dis_nombre = disNombre;
	}

	/**
	 * @return the dis_marca
	 */
	public String getDis_marca() {
		return dis_marca;
	}

	/**
	 * @param disMarca the dis_marca to set
	 */
	public void setDis_marca(final String disMarca) {
		dis_marca = disMarca;
	}

	/**
	 * @return the dis_modelo
	 */
	public String getDis_modelo() {
		return dis_modelo;
	}

	/**
	 * @param disModelo the dis_modelo to set
	 */
	public void setDis_modelo(final String disModelo) {
		dis_modelo = disModelo;
	}

	/**
	 * @return the dis_tipo
	 */
	public String getDis_tipo() {
		return dis_tipo;
	}

	/**
	 * @param disTipo the dis_tipo to set
	 */
	public void setDis_tipo(final String disTipo) {
		dis_tipo = disTipo;
	}

	/**
	 * @return the dis_centro
	 */
	public String getDis_centro() {
		return dis_centro;
	}

	/**
	 * @param disCentro the dis_centro to set
	 */
	public void setDis_centro(final String disCentro) {
		dis_centro = disCentro;
	}

	/**
	 * @return the dis_departamento
	 */
	public String getDis_departamento() {
		return dis_departamento;
	}

	/**
	 * @param disDepartamento the dis_departamento to set
	 */
	public void setDis_departamento(final String disDepartamento) {
		dis_departamento = disDepartamento;
	}

	/**
	 * @return the dis_ubicacion
	 */
	public String getDis_ubicacion() {
		return dis_ubicacion;
	}

	/**
	 * @param disUbicacion the dis_ubicacion to set
	 */
	public void setDis_ubicacion(final String disUbicacion) {
		dis_ubicacion = disUbicacion;
	}

	/**
	 * @return the dis_fecha_compra
	 */
	public String getDis_fecha_compra() {
		return dis_fecha_compra;
	}

	/**
	 * @param disFechaCompra the dis_fecha_compra to set
	 */
	public void setDis_fecha_compra(final String disFechaCompra) {
		dis_fecha_compra = disFechaCompra;
	}

	/**
	 * @return the dis_fin_garantia
	 */
	public String getDis_fin_garantia() {
		return dis_fin_garantia;
	}

	/**
	 * @param disFinGarantia the dis_fin_garantia to set
	 */
	public void setDis_fin_garantia(final String disFinGarantia) {
		dis_fin_garantia = disFinGarantia;
	}

	/**
	 * @return the dis_proveedor
	 */
	public String getDis_proveedor() {
		return dis_proveedor;
	}

	/**
	 * @param disProveedor the dis_proveedor to set
	 */
	public void setDis_proveedor(final String disProveedor) {
		dis_proveedor = disProveedor;
	}

	/**
	 * @return the dis_mantenimiento
	 */
	public String getDis_mantenimiento() {
		return dis_mantenimiento;
	}

	/**
	 * @param disMantenimiento the dis_mantenimiento to set
	 */
	public void setDis_mantenimiento(final String disMantenimiento) {
		dis_mantenimiento = disMantenimiento;
	}

	/**
	 * @return the dis_observaciones
	 */
	public String getDis_observaciones() {
		return dis_observaciones;
	}

	/**
	 * @param disObservaciones the dis_observaciones to set
	 */
	public void setDis_observaciones(final String disObservaciones) {
		dis_observaciones = disObservaciones;
	}
}
