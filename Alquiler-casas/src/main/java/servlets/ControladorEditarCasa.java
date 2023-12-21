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
@WebServlet("/editarcasa")
public class ControladorEditarCasa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorEditarCasa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Dao dao = new Dao();
			DaoCasa daoCasa = new DaoCasa(dao);
			Casa c = daoCasa.buscarPorId(id);
			request.setAttribute("casa", c);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		request.getRequestDispatcher("editarCasa.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=null;
		DaoCasa daoCasa;

		HttpSession sesion = request.getSession(true);
// recuperar datos en variables
		String provincia = request.getParameter("province");
		String region = request.getParameter("region");
		String direccion = request.getParameter("address");
		int habitaciones = Integer.parseInt(request.getParameter("rooms"));
		int camas = Integer.parseInt(request.getParameter("beds"));
		int banios = Integer.parseInt(request.getParameter("bathroom"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		try {
			dao = new Dao();
			daoCasa = new DaoCasa(dao);
			Casa c = daoCasa.buscarPorId(id);
			c.setProvincia(provincia);
			c.setRegion(region);
			c.setDireccion(direccion);
			c.setHabitaciones(habitaciones);
			c.setCamas(camas);
			c.setBanios(banios);
			boolean exito = daoCasa.modifica(c);
			if(exito) {
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Has editado la casa correctamente.");
				response.sendRedirect("miscasas");
			} else {
				// Error en la creación del usuario. Redirige al formulario otra vez
				sesion.setAttribute("error", "Ha habido algún error durante la edicion de la casa.");
				response.sendRedirect("editarcasa");
			}
		}catch(Exception e) {
			e.printStackTrace();
			//lanzamos la excepcion
			throw new RuntimeException(e.getMessage());
		}
	}

}
