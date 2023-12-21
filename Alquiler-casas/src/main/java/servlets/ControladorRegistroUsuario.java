package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dao.DaoUsuario;
import entidades.Usuario;

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
		Dao dao=null;
		DaoUsuario daoUsuario;

		HttpSession sesion = request.getSession(true);

		String nombre = request.getParameter("name");
		String apellidos = request.getParameter("surname");
		String telefono = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// Comprobamos que el correo sigue el formato con una expresión regular
		if (email == null || !email.matches("[^@]+@[^@.]+(\\.[^@.]+)*")) {
			sesion.setAttribute("error", "El correo no sigue el formato correcto");
			response.sendRedirect("registro");
			return;
		}
		
		try {
			dao = new Dao();
			daoUsuario = new DaoUsuario(dao);
			Usuario u = new Usuario();
			u.setNombre(nombre);
			u.setApellidos(apellidos);
			u.setTelefono(telefono);
			u.setEmail(email);
			u.setPassword(password);
			boolean exito = daoUsuario.inserta(u);
			if(exito) {
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Te has registrado correctamente como: " + u.getNombre());
				response.sendRedirect("login");
			} else {
				// Error en la creación del usuario. Redirige al formulario otra vez
				sesion.setAttribute("error", "Ha habido algún error durante el registro");
				response.sendRedirect("registrousuario");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
