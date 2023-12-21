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
			Dao dao=null;
			DaoUsuario daoUsuario;
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			try {
				dao = new Dao();
				daoUsuario = new DaoUsuario(dao);
				Usuario u = daoUsuario.buscarPorEmail(email);
				if(u!= null && u.getPassword().equals(password)) {
					//redirige a home
					sesion.setAttribute("success", "Has iniciado sesion como: " + u.getNombre());
					sesion.setAttribute("logged", u.getId());
					response.sendRedirect("");
					
				}else {
					//redirige a login
					sesion.setAttribute("error", "Correo o contraseña erróneos");
					response.sendRedirect("login");
				}
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		} else {
			sesion.setAttribute("error", "No puedes iniciar una sesión si ya tienes otra iniciada");
			
			response.sendRedirect("");
		}
	}
}
