package comunicacion;

import eventos.*;
import java.util.StringTokenizer;

public class ConversorEventos{
	
	public static Eventos pasaAEvento(String s){
		StringTokenizer tok=new StringTokenizer(s,"ç");
		String param1=tok.nextToken();
		String param2;
		
		if (param1.equals("bajada")){
			param1=tok.nextToken();
			param2=tok.nextToken();
			return new EventoBajada(param1,param2);
		}
		else if (param1.equals("turno")){
			return new EventoCambioTurno();			
		}
		else if (param1.equals("ataque")){
			param1=tok.nextToken();
			param2=tok.nextToken();
			return new EventoAtaque(param1,param2);
		}
		else if (param1.equals("defensa")){
			String param3,param4;
			int param5;
			param1=tok.nextToken();
			param2=tok.nextToken();
			param3=tok.nextToken();
			param4=tok.nextToken();
			param5=new Integer(tok.nextToken()).intValue();
			return new EventoDefensa(param1,param2,param3,param4,param5);
		}
		else if (param1.equals("findefensa")){
			return new EventoFinDefensa();
		}
		else if (param1.equals("iniciopartida")){
			param1=tok.nextToken();
			return new EventoInicioPartida(Boolean.getBoolean(param1));
		}
		else return null;
	}
	
	public static String pasaAString(Eventos e){
		return e.toString();
	}
	
}