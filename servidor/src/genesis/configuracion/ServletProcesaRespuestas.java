package genesis.configuracion;

import javax.servlet.http.*;
import javax.servlet.*;
import genesis.configuracion.*;
import java.io.*;
import genesis.configuracion.tipos.*;
import java.util.*;


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class ServletProcesaRespuestas extends HttpServlet {

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

		Opcion opcionCompuesta =
				GestorOpciones.getGestorOpciones().getOpcionPorNombre(path);

		if (!opcionCompuesta.getCompuesta()) {
			RequestDispatcher rq = request.getRequestDispatcher("MostrarOpcionCompuesta");
			rq.forward(request, response);
		}

		ArrayList informe = new ArrayList();
		Iterator hijos = ((OpcionCompuesta) opcionCompuesta).getOpciones().iterator();

		while (hijos.hasNext()) {
			Opcion actual = (Opcion) hijos.next();
			if (!actual.getCompuesta()) {
				String valorIntroducido = request.getParameter(actual.getNombre());
				estableceOpcion((OpcionSimple) actual, valorIntroducido, informe);
			}
		}

		request.setAttribute("nombre", opcionCompuesta.getNombre());
		request.setAttribute("path", path);
		request.setAttribute("opcionDescompuesta",
				GestorOpciones.descomponerOpcion(path));
		request.setAttribute("informe", informe);

		RequestDispatcher rd = request.getRequestDispatcher("mostrarRespuesta.jsp");
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


	/**
	 *  Description of the Method
	 *
	 *@param  opcion   Description of Parameter
	 *@param  valor    Description of Parameter
	 *@param  informe  Description of Parameter
	 */
	private void estableceOpcion(OpcionSimple opcion, String valor, Collection informe) {
		TipoOpcion tipoOpcion =
				GestorOpciones.getGestorOpciones().getTipo(opcion.getTipo());

		if (tipoOpcion.esValido(valor)) {
			// El valor introducido en el cuadro de texto
			// es válido
			Object nuevoValor = tipoOpcion.fromString(valor);
			if (!opcion.getValor().equals(nuevoValor)) {
				opcion.setValor(nuevoValor);
				informe.add(
						new InformeRespuesta(
						opcion.getNombre(),
						false,
						tipoOpcion.getDescripcion()
						)
						);
			}
		}
		else {
			// El valor introducido en el cuadro de texto
			// NO es válido
			informe.add(
					new InformeRespuesta(
					opcion.getNombre(),
					true,
					tipoOpcion.getDescripcion()
					)
					);
		}
	}

}
