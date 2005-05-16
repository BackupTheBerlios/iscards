package genesis.configuracion.arbol;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import genesis.configuracion.*;
import genesis.configuracion.arbol.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class AbrirNodo extends HttpServlet {

	/**
	 *  Description of the Method
	 *
	 *@param  request               Description of Parameter
	 *@param  response              Description of Parameter
	 *@exception  IOException       Description of Exception
	 *@exception  ServletException  Description of Exception
	 */
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Arbol arbol = (Arbol) session.getAttribute("arbol");

		String path = request.getParameter("path");
		if (path != null && arbol != null) {
			NodoVista objetivo = arbol.buscarNodo(path);
			if (objetivo != null) {
				objetivo.abrir();
				session.setAttribute("arbol", arbol);
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("MostrarArbol");
		rd.forward(request, response);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  request               Description of Parameter
	 *@param  response              Description of Parameter
	 *@exception  IOException       Description of Exception
	 *@exception  ServletException  Description of Exception
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
