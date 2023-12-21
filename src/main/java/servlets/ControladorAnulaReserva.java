package servlets;

import java.io.IOException;

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

import model.Ofrece;
import model.Reserva;

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
		HttpSession sesion = request.getSession(true);

		int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
		
		// Obtenemos el Entity Manager y Entity Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");//necesario
		EntityManager em = emf.createEntityManager();//necesario para consultas
		EntityTransaction tx = em.getTransaction(); //para hacer cambios general
		
		Reserva r = em.find(Reserva.class, reserva_id);
//		r.setId(reserva_id);
//		r = em.merge(r);

		try {
			tx.begin();
			em.remove(r);
			tx.commit();
			
			// Usuario creado. Redirige a login
			sesion.setAttribute("success", "Has anulado la reserva correctamente.");
			response.sendRedirect("");
		} catch (RollbackException re) {
			// Error en la creación del usuario. Redirige al formulario otra vez
			sesion.setAttribute("error", "Ha habido algún error durante la anulación de la reserva.");
			response.sendRedirect("anulareservacasa");
		}
	}

}
