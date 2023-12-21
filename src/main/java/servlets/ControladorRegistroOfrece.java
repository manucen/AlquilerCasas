package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Casa;
import model.Ofrece;

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
		HttpSession sesion = request.getSession(true);

		String semana = request.getParameter("week");
		int casa_id = Integer.parseInt(request.getParameter("casa_id"));
		
		// Obtenemos el Entity Manager y Entity Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Ofrece o = new Ofrece();
		LocalDate fecha = Date.valueOf(semana).toLocalDate();
		fecha = fecha.minusDays(fecha.getDayOfWeek().getValue() - 1);
		o.setFechaInicioSemana(java.sql.Date.valueOf(fecha.toString()));
		Casa c = new Casa();
		c.setId(casa_id);
		o.setCasa(c);

		try {
			tx.begin();
			em.persist(o);
			tx.commit();
		
			// Oferta creada. Redirige a login
			sesion.setAttribute("success", "Has hecho la oferta correctamente.");
			response.sendRedirect("");
		} catch (RollbackException re) {
			// Error en la creación de la oferta. Redirige al formulario otra vez
			sesion.setAttribute("error", "Ha habido algún error durante el registro de la oferta.");
			response.sendRedirect("registroofertacasa");
		}
	}

}
