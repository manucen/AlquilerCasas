package servlets;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/editarperfil")
public class ControladorEditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorEditarPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		int id = (int)sesion.getAttribute("logged");
		
		// Obtenemos el Entity Manager y Entity Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
		
		Usuario result = em.find(Usuario.class, id);
		request.setAttribute("usuario", result);
		
		request.getRequestDispatcher("editarPerfil.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);

		String nombre = request.getParameter("name");
		String apellidos = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telefono = request.getParameter("phone");
		int id = (int)sesion.getAttribute("logged");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Usuario p = em.find(Usuario.class, id);
		p.setNombre(nombre);
		p.setApellido(apellidos);
		p.setEmail(email);
		p.setPassword(password);
		p.setTelefono(telefono);

		try {
			tx.begin();
			em.persist(p);
			tx.commit();
			
			// Usuario creado. Redirige a login
			sesion.setAttribute("success", "Has editado tu perfil correctamente.");
			response.sendRedirect("");
		} catch (RollbackException re) {
			// Error en la creación del usuario. Redirige al formulario otra vez
			sesion.setAttribute("error", "Ha habido algún error durante la edicion de tu perfil.");
			response.sendRedirect("editarperfil");
		}
	}

}
