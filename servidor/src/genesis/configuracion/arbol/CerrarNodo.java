package genesis.configuracion.arbol;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import genesis.configuracion.*;
import genesis.configuracion.arbol.*;

public class CerrarNodo extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Arbol arbol = (Arbol) session.getAttribute("arbol");
		
		String path = request.getParameter("path");
		if (path != null && arbol != null) {
			NodoVista objetivo = arbol.buscarNodo(path);
			if (objetivo != null) {
				objetivo.cerrar();
				session.setAttribute("arbol", arbol);
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("MostrarArbol");
		rd.forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
	
}
