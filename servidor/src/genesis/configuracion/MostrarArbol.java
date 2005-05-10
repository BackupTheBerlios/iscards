package genesis.configuracion;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import genesis.configuracion.*;
import genesis.configuracion.arbol.*;

public class MostrarArbol extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Arbol arbol = (Arbol) session.getAttribute("arbol");
		
		if (arbol == null) {
			GestorOpciones go = GestorOpciones.getGestorOpciones();
			Opcion opcion = go.getOpcionPorNombre("/");
			arbol = new Arbol((OpcionCompuesta)opcion);
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
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
	
}
