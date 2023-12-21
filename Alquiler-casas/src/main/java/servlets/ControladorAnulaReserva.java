package servlets;

import java.io.IOException;
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
 * Servlet implementation class ControladorAnulaReserva
 */
@WebServlet("/anulareservacasa")
public class ControladorAnulaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorAnulaReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("anulaReserva.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=null;
		DaoReserva daoReserva;

		HttpSession sesion = request.getSession(true);

		int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
		
		
		try {
			dao = new Dao();
			daoReserva = new DaoReserva(dao);
			Reserva r = new Reserva();
			r.setId(reserva_id);
			boolean exito = daoReserva.elimina(r);
			if(exito) {
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Has anulado la reserva correctamente.");
				response.sendRedirect("");
			} else {
				// Error en la creación del usuario. Redirige al formulario otra vez
				sesion.setAttribute("error", "Ha habido algún error durante la anulación de la reserva.");
				response.sendRedirect("anulaReserva");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
