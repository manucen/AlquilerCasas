package servlets;

import java.io.IOException;
import java.sql.Date;
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

import model.Ofrece;
import model.Reserva;
import model.Usuario;

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
		HttpSession sesion = request.getSession(true);

		int oferta_id = Integer.parseInt(request.getParameter("oferta_id"));
		int usuario_id = (int)sesion.getAttribute("logged");
		
		// Obtenemos el Entity Manager y Entity Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			Usuario u = new Usuario();
			u.setId(usuario_id);
			Reserva r = new Reserva();
			r.setUsuario(u);

			tx.begin();//iniciar transaccion 
			em.persist(r);//operacion que queremos q lleve a cabo bdd
			tx.commit();//enviar trasaccion
			
			try {
				Ofrece o = em.find(Ofrece.class, oferta_id);
				o.setReserva(r);
				
				tx.begin();
				em.persist(o);
				tx.commit();
				
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Has reservado la casa correctamente.");
				response.sendRedirect("");
			} catch (RollbackException re) {
				sesion.setAttribute("error", "Ha habido algún error añadiendo la reserva a la oferta de la casa.");
				response.sendRedirect("registroreservacasa");
			}
		} catch (RollbackException re) {
			sesion.setAttribute("error", "Ha habido algún error durante la creación la reserva.");
			response.sendRedirect("registroreservacasa");
		}
	}
}
