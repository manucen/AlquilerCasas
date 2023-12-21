package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dao.DaoCasa;
import entidades.Casa;

/**
 * Servlet implementation class ControladorHome
 */
@WebServlet("/miscasas")
public class ControladorMisCasas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorMisCasas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao;
		DaoCasa daoCasa;
		HttpSession sesion = request.getSession(true);
		try {
			dao = new Dao();
			daoCasa = new DaoCasa(dao);
			List<Casa> casas = daoCasa.buscarPorUsuario((int)sesion.getAttribute("logged"));
			request.setAttribute("casas", casas);
		} catch (Exception e) {
			// imprimir traza error
			e.printStackTrace();
		}
		//abrimos lista de casas
		request.getRequestDispatcher("miscasas.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(400, "Esta URL no admite método POST");
	}

}
