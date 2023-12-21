package entidades;

import java.util.List;

public class Casa {
	
	private int id;	
	private String direccion;
	private String provincia;
	private String region;
	private int habitaciones;
	private int camas;
	private int banios;
	private int puntos;
	private int usuario_id; //Referencia al objeto usuario que posee esta casa
	private List<Ofrece> ofertas;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	public int getCamas() {
		return camas;
	}

	public void setCamas(int camas) {
		this.camas = camas;
	}

	public int getBanios() {
		return banios;
	}

	public void setBanios(int banios) {
		this.banios = banios;
	}
	public int getUsuarioId() {
		return usuario_id;
	}
	public void setUsuarioId(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getPuntos() {
		return puntos;
	}

	public void setOfertas(List<Ofrece> ofertas) {
		this.ofertas = ofertas;
	}
	public List<Ofrece> getOfertas() {
		return ofertas;
	}

	
	@Override
	public String toString() {
		return "Casa [id=" + id + ", direccion=" + direccion + ", provincia=" + provincia + ", region=" + region
				+ ", habitaciones=" + habitaciones + ", camas=" + camas + ", banios=" + banios + ", usuario_id="
				+ usuario_id + ", puntos=\" + puntos + \"]";
	}
}
