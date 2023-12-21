package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ofrece database table.
 * 
 */
@Entity
@Table(name="ofrece")
@NamedQuery(name="Ofrece.findAll", query="SELECT o FROM Ofrece o")
public class Ofrece implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio_semana", nullable=false)
	private Date fechaInicioSemana;

	//bi-directional many-to-one association to Casa
	@ManyToOne
	@JoinColumn(name="casa_id", nullable=false)
	private Casa casa;

	//bi-directional many-to-one association to Reserva
	@ManyToOne
	@JoinColumn(name="reserva_id")
	private Reserva reserva;

	public Ofrece() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaInicioSemana() {
		return this.fechaInicioSemana;
	}

	public void setFechaInicioSemana(Date fechaInicioSemana) {
		this.fechaInicioSemana = fechaInicioSemana;
	}

	public Casa getCasa() {
		return this.casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public Reserva getReserva() {
		return this.reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}