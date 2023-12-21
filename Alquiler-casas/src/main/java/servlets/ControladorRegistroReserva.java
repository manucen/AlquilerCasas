package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dao.DaoOfrece;
import dao.DaoReserva;
import entidades.Ofrece;
import entidades.Reserva;

/**
 * Servlet implementation class ControladorReserva
 */
@WebServlet("/registroreservacasa")
public class ControladorRegistroReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorRegistroReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registroReserva.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=null;
		DaoReserva daoReserva;
		DaoOfrece daoOfrece;

		HttpSession sesion = request.getSession(true);

		int oferta_id = Integer.parseInt(request.getParameter("oferta_id"));
		int usuario_id = (int)sesion.getAttribute("logged");
		
		
		try {
			dao = new Dao();
			daoReserva = new DaoReserva(dao);
			Reserva r = new Reserva();
			r.setUsuarioId(usuario_id);
			boolean exito = daoReserva.inserta(r);
			if(exito) {
				daoOfrece = new DaoOfrece(dao);
				Ofrece o = new Ofrece();
				o.setId(oferta_id);
				o.setReserva(r);
				daoOfrece.reserva(o);
				
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Has reservado la casa correctamente.");
				response.sendRedirect("");
			} else {
				// Error en la creación del usuario. Redirige al formulario otra vez
				sesion.setAttribute("error", "Ha habido algún error durante la reserva de la casa.");
				response.sendRedirect("registroReserva");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
