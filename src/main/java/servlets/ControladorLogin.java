package servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

/**
 * Servlet implementation class ControladorLogin
 */
@WebServlet("/login")
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession sesion =request.getSession(true);
	
		
		if (sesion.getAttribute("logged") == null) {
			// Obtenemos el Entity Manager y Entity Transaction
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			List<Usuario> results = em.createQuery(
					"SELECT u FROM Usuario u where u.email = :email AND u.password = :password"
					).setParameter("email", email).setParameter("password", password).getResultList();
			Usuario u; // = em.find(Usuario.class, 1L);
			
			if(results.size() > 0) {
				u = results.get(0);
				//redirige a home
				sesion.setAttribute("success", "Has iniciado sesion como: " + u.getNombre());
				sesion.setAttribute("logged", u.getId());
				response.sendRedirect("");
				
			} else {
				//redirige a login
				sesion.setAttribute("error", "Correo o contraseña erróneos");
				response.sendRedirect("login");
			}
		} else {
			sesion.setAttribute("error", "No puedes iniciar una sesión si ya tienes otra iniciada");
			
			response.sendRedirect("");
		}
	}
}
