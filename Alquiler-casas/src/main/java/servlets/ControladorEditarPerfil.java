package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dao.DaoCasa;
import dao.DaoUsuario;
import entidades.Casa;
import entidades.Usuario;

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
		try {
			Dao dao = new Dao();
			DaoUsuario daoUsuario = new DaoUsuario(dao);
			Usuario p = daoUsuario.buscarPorId(id);
			request.setAttribute("usuario", p);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		request.getRequestDispatcher("editarPerfil.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=null;
		DaoUsuario daoPerfil;

		HttpSession sesion = request.getSession(true);

		String nombre = request.getParameter("name");
		String apellidos = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telefono = request.getParameter("phone");
		int id = (int)sesion.getAttribute("logged");
		
		
		try {
			dao = new Dao();
			daoPerfil = new DaoUsuario(dao);
			Usuario p = daoPerfil.buscarPorId(id);
			p.setNombre(nombre);
			p.setApellidos(apellidos);
			p.setEmail(email);
			p.setPassword(password);
			p.setTelefono(telefono);
			boolean exito = daoPerfil.modifica(p);
			if(exito) {
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Has editado tu perfil correctamente.");
				response.sendRedirect("");
			} else {
				// Error en la creación del usuario. Redirige al formulario otra vez
				sesion.setAttribute("error", "Ha habido algún error durante la edicion de tu perfil.");
				response.sendRedirect("editarperfil");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
