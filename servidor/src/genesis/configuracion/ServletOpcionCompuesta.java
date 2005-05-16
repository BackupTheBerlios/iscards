package genesis.configuracion;

import javax.servlet.http.*;
import javax.servlet.*;
import genesis.configuracion.*;
import java.io.*;
import java.util.*;


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class ServletOpcionCompuesta extends HttpServlet {


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

		String path = request.getParameter("path");

		if (path == null || path.equals("/")) {
			path = "";
		}


		GestorOpciones gestorOpciones = GestorOpciones.getGestorOpciones();
		Opcion opcionCompuesta = gestorOpciones.getOpcionPorNombre(path);

		ArrayList opcionesCompuestas = new ArrayList();
		ArrayList opcionesSimples = new ArrayList();

		if (opcionCompuesta != null && opcionCompuesta.getCompuesta()) {
			Iterator it =
					((OpcionCompuesta) opcionCompuesta).getOpciones().iterator();
			while (it.hasNext()) {
				Opcion actual = (Opcion) it.next();
				if (actual.getCompuesta()) {
					opcionesCompuestas.add(
							new OpcionCompuestaVista(
							actual.getDescripcion(),
							actual.getNombre(),
							path + "/" + actual.getNombre()
							)
							);
				}
				else {
					opcionesSimples.add(
							new OpcionSimpleVista(
							actual.getDescripcion(),
							actual.getNombre(),
							String.valueOf(((OpcionSimple) actual).getTipo()),
							((OpcionSimple) actual).getValor().toString()
							)
							);
				}
			}

		}

//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();

		if (opcionCompuesta == null) {
			request.getRequestDispatcher("MostrarOpcionCompuesta?path=/").
					forward(request, response);

		}

		/*
		 *  out.println("Path = " + path);
		 *  out.println(descomponerOpcion(path));
		 *  out.println("<br> Opciones simples: " + opcionesSimples);
		 *  out.println("<br> Opciones compuestas: " + opcionesCompuestas);
		 */
		request.setAttribute("nombre", opcionCompuesta.getNombre());
		request.setAttribute("simples", opcionesSimples);
		request.setAttribute("compuestas", opcionesCompuestas);
		request.setAttribute("opcionDescompuesta",
				GestorOpciones.descomponerOpcion(path));
		request.setAttribute("path", path);

		RequestDispatcher rd = request.getRequestDispatcher("mostrarGrupo.jsp");
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
