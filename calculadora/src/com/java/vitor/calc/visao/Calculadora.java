package com.java.vitor.calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;

@SuppressWarnings("serial")

public class Calculadora extends JFrame {

	public Calculadora() {
		
		criarLayout();
		
		// TODO ajeitar a barra superior 
		
		Font fonteUsada = new Font("Helvetica Neue", Font.PLAIN, 16);
		
		setFont(fonteUsada);
		
		setTitle("VITOR'S CALCULATOR");
		
		setSize(250, 330);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	private void criarLayout() {
		
		setLayout(new BorderLayout());
		
		Display_Informacao display = new Display_Informacao();
		
		display.setPreferredSize(new Dimension(250, 55));
		
		Teclado_Intercao teclado = new Teclado_Intercao();
	
		add(display, BorderLayout.NORTH);
		
		add(teclado, BorderLayout.CENTER);
		
		
	}

	public static void main(String[] args) {
		
		new Calculadora();
		
	}
	
}
