package entidades;

public class Ofrece {
	private int id;
	private int casa_id;
//	private int reserva_id;
	private Reserva reserva;
	private java.sql.Date fechaInicioSemana;
	//private Semana semana; //Referencia al objeto semana que posee esta casa
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCasaId() {
		return casa_id;
	}
	public void setCasaId(int casa_id) {
		this.casa_id = casa_id;
	}
//	public int getReservaId() {
//		return reserva_id;
//	}
//	public void setReservaId(int reserva_id) {
//		this.reserva_id = reserva_id;
//	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public java.sql.Date getFechaInicioSemana() {
		return fechaInicioSemana;
	}
	public void setFechaInicioSemana(java.sql.Date fechaInicioSemana) {
		this.fechaInicioSemana = fechaInicioSemana;
	}
	/*public Semana getSemana() {
		return semana;
	}
	public void setSemana(Semana semana) {
		this.semana = semana;
	}*/
	
	@Override
	public String toString() {
		return "Ofrece [id=" + id + ", casa_id=" + casa_id + ", reserva=" + reserva + ", fechaInicioSemana=" + fechaInicioSemana + "]";
	}
	
	
}
