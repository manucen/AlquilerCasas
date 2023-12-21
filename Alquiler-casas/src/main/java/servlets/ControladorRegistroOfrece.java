package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dao.DaoCasa;
import dao.DaoOfrece;
import entidades.Casa;
import entidades.Ofrece;

/**
 * Servlet implementation class ControladorRegistroOfrece
 */
@WebServlet("/registroofertacasa")
public class ControladorRegistroOfrece extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorRegistroOfrece() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registroOfrece.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=null;
		DaoOfrece daoOfrece;

		HttpSession sesion = request.getSession(true);

		String semana = request.getParameter("week");
		int casa_id = Integer.parseInt(request.getParameter("casa_id"));
		
		
		try {
			dao = new Dao();
			daoOfrece = new DaoOfrece(dao);
			Ofrece o = new Ofrece();
			LocalDate fecha = Date.valueOf(semana).toLocalDate();
			fecha = fecha.minusDays(fecha.getDayOfWeek().getValue() - 1);
			o.setFechaInicioSemana(java.sql.Date.valueOf(fecha.toString()));
			o.setCasaId(casa_id);
			boolean exito = daoOfrece.inserta(o);
			if(exito) {
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Has hecho la oferta correctamente.");
				response.sendRedirect("");
			} else {
				// Error en la creación del usuario. Redirige al formulario otra vez
				sesion.setAttribute("error", "Ha habido algún error durante el registro de la oferta.");
				response.sendRedirect("registroOfrece");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
