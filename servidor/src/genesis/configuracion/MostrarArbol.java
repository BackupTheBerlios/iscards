package genesis.configuracion;

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
public class MostrarArbol extends HttpServlet {

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

		if (arbol == null) {
			GestorOpciones go = GestorOpciones.getGestorOpciones();
			Opcion opcion = go.getOpcionPorNombre("/");
			arbol = new Arbol((OpcionCompuesta) opcion);
			session.setAttribute("arbol", arbol);
		}

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<html><head>");
		pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/genesis/admin/arbol.css\">");
		pw.println("</head><body>");
		pw.println(arbol.toHTML());
		pw.println("</body></html>");
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
