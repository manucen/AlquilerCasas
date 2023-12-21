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

import model.Usuario;

/**
 * Servlet implementation class ControladorUsuario
 */
@WebServlet("/registrousuario")
public class ControladorRegistroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorRegistroUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registroUsuario.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);

		String nombre = request.getParameter("name");
		String apellidos = request.getParameter("surname");
		String telefono = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// Comprobamos que el correo sigue el formato con una expresión regular
		if (email == null || !email.matches("[^@]+@[^@.]+(\\.[^@.]+)*")) {
			sesion.setAttribute("error", "El correo no sigue el formato correcto");
			response.sendRedirect("registrousuario");
			return;
		}
		
		// Obtenemos el Entity Manager y Entity Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setApellido(apellidos);
		u.setTelefono(telefono);
		u.setEmail(email);
		u.setPassword(password);
		
		try {
			tx.begin();
			em.persist(u);
			tx.commit();
			
			// Usuario creado. Redirige a login
			sesion.setAttribute("success", "Te has registrado correctamente como: " + u.getNombre());
			response.sendRedirect("login");
		} catch (RollbackException re) {
			// Error en la creación del usuario. Redirige al formulario otra vez
			sesion.setAttribute("error", "Ha habido algún error durante el registro");
			response.sendRedirect("registrousuario");
		}
	}

}
