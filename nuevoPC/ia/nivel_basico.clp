;***********************************************************;
;										;
;	NIVEL BÁSICO PARA LA I.A. DEL JUEGO DE CARTAS		;
;										;
;***********************************************************;

;;; PLANTILLAS

; plantilla para los hechos de las cartas. Los hechizos y conjuros tendrán valores de cero por defecto
; en el ataque, la defensa y las vidas.


(deftemplate carta
	(slot jugador)  ; si es carta del PC o de la PERSONA que juega contra él.
;	(slot ataque (type NUMBER)(default 0)) ; el valor en ataque
;	(slot defensa (type NUMBER)(default 0)) ; el valor en defensa
;	(slot vidas (type NUMBER)(default 0)) ; las vidas que le quedan a la carta
	(slot coste (type NUMBER)) ; los manás que vale bajar la carta
;	(slot puntos (type NUMBER)) ; el valor de la carta en el juego
;	(slot tipo) ; si es criatura, conjuro, hechizo.
	(slot estado) ; si la carta está enderezada o girada
	(slot codigo) ; el código que identifica a la carta
	(slot lugar)) ; si la carta esta en la mesa o en la mano


(deftemplate mana
	(slot numero (type NUMBER)(default 0)))


(deffacts cartas
	(carta (jugador PC)(coste 1)(lugar mano)(codigo h1))
	(carta (jugador PC)(coste 1)(lugar mano)(codigo h2))
	(carta (jugador PC)(coste 2)(lugar mano)(codigo h3))
	(carta (jugador PC)(coste 3)(lugar mano)(codigo h4)))

(deffacts manas
	(mana (numero 5)))

;;; REGLAS

; Regla para bajar las carta, las va bajando aleatoriamente.
; mira si hay maná (si es mayor que 0)
; comprueba que hay alguna carta cuyo coste es menor que el mana
; en caso de que se cumplan las condiciones, resta el valor de la carta a maná
; y se modifica el estado de la carta, bajándola a la mesa

(defrule bajarCartas
	?m <- (mana (numero ?n))
	(test (> ?n  0))
	?carta <- (carta (jugador PC)(coste ?c)(lugar mano))
	(test ( <= ?c ?n))
     =>
	(bind ?n (- ?n ?c))
	(modify ?m(numero ?n))
	(printout t "el mana es" ?n crlf)
	(modify ?carta(lugar mesa)(estado enderezado)))


	




;(defrule algBloqueo	