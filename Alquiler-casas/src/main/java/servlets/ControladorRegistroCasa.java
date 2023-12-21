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
		Dao dao=null;
		DaoCasa daoCasa;

		HttpSession sesion = request.getSession(true);

		String provincia = request.getParameter("province");
		String region = request.getParameter("region");
		String direccion = request.getParameter("address");
		int habitaciones = Integer.parseInt(request.getParameter("rooms"));
		int camas = Integer.parseInt(request.getParameter("beds"));
		int banios = Integer.parseInt(request.getParameter("bathroom"));
		
		
		
		try {
			dao = new Dao();
			daoCasa = new DaoCasa(dao);
			Casa c = new Casa();
			c.setProvincia(provincia);
			c.setRegion(region);
			c.setDireccion(direccion);
			c.setHabitaciones(habitaciones);
			c.setCamas(camas);
			c.setBanios(banios);
			int numero = (int) (Math.random() * 900 + 100);
			c.setPuntos(numero);
			c.setUsuarioId((int)sesion.getAttribute("logged"));
			boolean exito = daoCasa.inserta(c);
			if(exito) {
				// Usuario creado. Redirige a login
				sesion.setAttribute("success", "Has registrado la casa correctamente.");
				response.sendRedirect("");
			} else {
				// Error en la creación del usuario. Redirige al formulario otra vez
				sesion.setAttribute("error", "Ha habido algún error durante el registro de la casa.");
				response.sendRedirect("registrocasa");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
