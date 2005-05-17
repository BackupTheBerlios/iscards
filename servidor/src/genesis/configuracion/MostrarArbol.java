package genesis.configuracion;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import genesis.configuracion.*;
import genesis.configuracion.arbol.*;

/**
 *  Servlet que muestra el �rbol en el frame izquierdo de la interfaz de
 * la herramienta de administraci�n (configuraci�n de opciones)
 *
 *@author    Manuel Montenegro
 */
public class MostrarArbol extends HttpServlet {

	/**
	 *  Env�a el c�digo HTML del �rbol a la salida est�ndar. Para ello utiliza
	 * el m�todo toHTML() del �rbol. El �rbol se guarda como un atributo de la
	 * sesi�n.
	 *
	 */
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Arbol arbol = (Arbol) session.getAttribute("arbol");

		// Si no se ha encontrado el �rbol en la sesi�n, lo creamos y lo
		// ponemos en la sesi�n
		if (arbol == null) {
			GestorOpciones go = GestorOpciones.getGestorOpciones();
			Opcion opcion = go.getOpcionPorNombre("/");
			arbol = new Arbol((OpcionCompuesta) opcion);
			session.setAttribute("arbol", arbol);
		}

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// Cabecera de la p�gina
		pw.println("<html><head>");
		pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/genesis/admin/arbol.css\">");
		pw.println("</head><body>");
		// �rbol
		pw.println(arbol.toHTML());
		// Pie de la p�gina
		pw.println("</body></html>");
	}


	/**
	 *  Llama al m�todo doGet
	 *
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
