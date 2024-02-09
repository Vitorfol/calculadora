package com.java.vitor.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private Memoria() {

	}

	private static Op ultimaOp = null;
	private static boolean substituir = false;
	private static String infAtual = "";
	private static String infArmazenada = "";

	public static String getInfAtual() {
		if (infAtual.length() == 1 && infAtual.contains(",")) {
			return "0,";
		}
		if (infAtual.isEmpty()) {
			return "0";
		} else {
			return infAtual;
		}
	}

	static List<MemoriaObserver> observers = new ArrayList<>();

	public static void registerObserver(MemoriaObserver e) {
		observers.add(e);
	}

	public static void novoComando(String tecla) {
		
		if (infAtual.startsWith(",")) {
			infAtual = "0,";
		}
		
		Op operacao = detectar(tecla);

		switch (operacao) {

		case NADA:
			return;

		case NUMERO:
		case VIRGULA:

			if (operacao == Op.NUMERO || (operacao == Op.VIRGULA && (!infAtual.contains(",") && ".".equals(tecla)))) {
				
				if (operacao == Op.VIRGULA) {
					tecla = ",";
				}
				if (operacao == Op.VIRGULA && infAtual.isEmpty()) {
					infAtual = "0";
				}
				if (substituir == false) {
					infAtual += tecla;
				} else {
					infAtual = tecla;
				}
				substituir = false;
			}
			break;

		case ZERAR:

			infAtual = "";
			infArmazenada = "";
			substituir = false;
			ultimaOp = null;
			break;

		case TROCAR_SINAL:

			if (infAtual.isEmpty()) {
				return;
			}

			try {
				Integer aux = (-1) * Integer.parseInt(infAtual);
				infAtual = String.valueOf(aux);
			} catch (NumberFormatException e) {
				String aux = infAtual.replace(",", ".");
				Double aux2 = (-1.0) * Double.parseDouble(aux);
				aux = String.valueOf(aux2).replace(".", ",");
				infAtual = aux;
			}

			infArmazenada = "";
			ultimaOp = null;
			substituir = false;
			break;

		case PORCENTAGEM:

			if (infAtual.isEmpty()) {
				return;
			}

			try {
				String aux = infAtual.replace(",", ".");
				Integer auxiliar = Integer.parseInt(aux) * (-1);
				auxiliar = Integer.parseInt(aux) / 100;
				if (auxiliar * 100 != Integer.parseInt(aux)) {
					throw new NumberFormatException();
				}
				aux = String.valueOf(auxiliar).replace(".", ",");
				infAtual = aux;
			} catch (NumberFormatException e) {
				String aux = infAtual.replace(",", ".");
				Double auxiliar = Double.parseDouble(aux) / 100.0;
				aux = String.valueOf(auxiliar).replace(".", ",");
				infAtual = aux;
			}

			infArmazenada = "";
			ultimaOp = null;
			substituir = false;
			break;

		case DIVIDIR:
		case MULTIPLICAR:
		case SOMAR:
		case SUBTRAIR:

			try {
				if (infArmazenada != infAtual) {
					if (operacao == ultimaOp) {
						return;
					}
					infAtual = retornoOperacao(ultimaOp);
					infArmazenada = infAtual;
					break;
				}

			} catch (RuntimeException e) {
				
			} finally {
				ultimaOp = operacao;
			}

			
		case EQUALIZAR:
			
			try {
				if (operacao == ultimaOp) {
					return;
				}
				infAtual = retornoOperacao(ultimaOp);
				infArmazenada = infAtual;
				ultimaOp = operacao;
				break;
			} catch (RuntimeException e) {
				
			}

		}
		
		if  (infArmazenada.isEmpty()) {
			infArmazenada = "0";
		}

		observers.forEach(o -> o.novoValor(getInfAtual()));
	}

	private static String retornoOperacao(Op operacao) {

		substituir = true;

		if (ultimaOp == null || ultimaOp == Op.EQUALIZAR) {
			return infAtual;
		}

		String auxAtual = infAtual.replace(",", ".");
		String auxArmazenada = infArmazenada.replace(",", ".");

		Double numeroAtual = Double.parseDouble(auxAtual);
		Double numeroArmazenado = Double.parseDouble(auxArmazenada);
		Double resultado = 0.0;

		if (operacao == Op.SOMAR) {
			resultado = numeroAtual + numeroArmazenado;
		} else if (operacao == Op.SUBTRAIR) {
			resultado = numeroArmazenado - numeroAtual;
		} else if (operacao == Op.MULTIPLICAR) {
			resultado = numeroAtual * numeroArmazenado;
		} else if (operacao == Op.DIVIDIR) {
			if (numeroAtual == 0) {
				return "Error";
			}
			resultado = numeroArmazenado / numeroAtual;
		}

		String aux = resultado.toString().replace(".", ",");

		if (aux.endsWith(",0")) {
			return aux.replace(",0", "");
		}

		return aux;

	}

	private static Op detectar(String tecla) {

		if (infAtual.isEmpty() && tecla.equals("0")) {
			return Op.NADA; 
		}

		try {
			Integer.parseInt(tecla);
			return Op.NUMERO;
		} catch (NumberFormatException e) {
			switch (tecla) {
			case "AC":
				return Op.ZERAR;
			case "+/-":
				return Op.TROCAR_SINAL;
			case "%":
				return Op.PORCENTAGEM;
			case "÷":
				return Op.DIVIDIR; 
			case "x":
				return Op.MULTIPLICAR;
			case "-":
				return Op.SUBTRAIR;
			case "+":
				return Op.SOMAR; 
			case "=":
				return Op.EQUALIZAR;
			case ".":
				return Op.VIRGULA; 
			}
			return Op.NADA;
		}
	}

}
