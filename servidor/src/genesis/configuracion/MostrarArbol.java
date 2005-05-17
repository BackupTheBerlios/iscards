package genesis.configuracion;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import genesis.configuracion.*;
import genesis.configuracion.arbol.*;

/**
 *  Servlet que muestra el árbol en el frame izquierdo de la interfaz de
 * la herramienta de administración (configuración de opciones)
 *
 *@author    Manuel Montenegro
 */
public class MostrarArbol extends HttpServlet {

	/**
	 *  Envía el código HTML del árbol a la salida estándar. Para ello utiliza
	 * el método toHTML() del árbol. El árbol se guarda como un atributo de la
	 * sesión.
	 *
	 */
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Arbol arbol = (Arbol) session.getAttribute("arbol");

		// Si no se ha encontrado el árbol en la sesión, lo creamos y lo
		// ponemos en la sesión
		if (arbol == null) {
			GestorOpciones go = GestorOpciones.getGestorOpciones();
			Opcion opcion = go.getOpcionPorNombre("/");
			arbol = new Arbol((OpcionCompuesta) opcion);
			session.setAttribute("arbol", arbol);
		}

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// Cabecera de la página
		pw.println("<html><head>");
		pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/genesis/admin/arbol.css\">");
		pw.println("</head><body>");
		// Árbol
		pw.println(arbol.toHTML());
		// Pie de la página
		pw.println("</body></html>");
	}


	/**
	 *  Llama al método doGet
	 *
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
