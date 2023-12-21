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

import model.Casa;
import model.Usuario;

/**
 * Servlet implementation class ControladorUsuario
 */
@WebServlet("/registrocasa")
public class ControladorRegistroCasa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorRegistroCasa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registroCasa.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);

		String provincia = request.getParameter("province");
		String region = request.getParameter("region");
		String direccion = request.getParameter("address");
		int habitaciones = Integer.parseInt(request.getParameter("rooms"));
		int camas = Integer.parseInt(request.getParameter("beds"));
		int banios = Integer.parseInt(request.getParameter("bathroom"));
		
		// Obtenemos el Entity Manager y Entity Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Casa c = new Casa();
		c.setProvincia(provincia);
		c.setRegion(region);
		c.setDireccion(direccion);
		c.setHabitaciones(habitaciones);
		c.setCamas(camas);
		c.setBanio(banios);
		int numero = (int) (Math.random() * 900 + 100);
		c.setPuntosdia(numero);
		Usuario u = em.find(Usuario.class, (int)sesion.getAttribute("logged"));
//		u.setId((int)sesion.getAttribute("logged"));
		c.setUsuario(u);

		try {
			tx.begin();
			em.persist(c);
			tx.commit();
			
			// Casa creada. Redirige a login
			sesion.setAttribute("success", "Has registrado la casa correctamente.");
			response.sendRedirect("");
		} catch (RollbackException re) {
			// Error en la creación de la casa. Redirige al formulario otra vez
			sesion.setAttribute("error", "Ha habido algún error durante el registro de la casa.");
			response.sendRedirect("registrocasa");
		}
	}

}
