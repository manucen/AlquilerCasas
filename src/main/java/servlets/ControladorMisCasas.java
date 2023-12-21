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

import model.Casa;

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
		HttpSession sesion = request.getSession(true);
		
		// Obtenemos el Entity Manager y Entity Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba JPA Manuel");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		List<Casa> results = em.createQuery("SELECT c FROM Casa c WHERE c.usuario.id = :usuario_id")
				.setParameter("usuario_id", (int)sesion.getAttribute("logged")).getResultList();
		request.setAttribute("casas", results);
		
		request.getRequestDispatcher("miscasas.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(400, "Esta URL no admite método POST");
	}

}
