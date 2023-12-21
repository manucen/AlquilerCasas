package entidades;

public class Reserva {
	private int id;	
	private int usuario_id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuarioId() {
		return usuario_id;
	}

	public void setUsuarioId(int usuario_id) {
		this.usuario_id = usuario_id;
	}


		
		@Override
		public String toString() {
			return "Reserva [id=" + id + ", usuario_id=" + usuario_id + "]";
		}
}
