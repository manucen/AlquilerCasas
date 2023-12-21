package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the casa database table.
 * 
 */
@Entity
@Table(name="casa")
@NamedQuery(name="Casa.findAll", query="SELECT c FROM Casa c")
public class Casa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private int banio;

	private int camas;

	@Column(length=43)
	private String direccion;

	private int habitaciones;

	@Column(length=43)
	private String provincia;

	private int puntosdia;

	@Column(length=43)
	private String region;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Ofrece
	@OneToMany(mappedBy="casa")
	private List<Ofrece> ofreces;

	public Casa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBanio() {
		return this.banio;
	}

	public void setBanio(int banio) {
		this.banio = banio;
	}

	public int getCamas() {
		return this.camas;
	}

	public void setCamas(int camas) {
		this.camas = camas;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getHabitaciones() {
		return this.habitaciones;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getPuntosdia() {
		return this.puntosdia;
	}

	public void setPuntosdia(int puntosdia) {
		this.puntosdia = puntosdia;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Ofrece> getOfreces() {
		return this.ofreces;
	}

	public void setOfreces(List<Ofrece> ofreces) {
		this.ofreces = ofreces;
	}

	public Ofrece addOfrece(Ofrece ofrece) {
		getOfreces().add(ofrece);
		ofrece.setCasa(this);

		return ofrece;
	}

	public Ofrece removeOfrece(Ofrece ofrece) {
		getOfreces().remove(ofrece);
		ofrece.setCasa(null);

		return ofrece;
	}

}