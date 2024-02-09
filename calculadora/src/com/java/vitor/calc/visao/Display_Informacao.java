package com.java.vitor.calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.java.vitor.calc.modelo.Memoria;
import com.java.vitor.calc.modelo.MemoriaObserver;

@SuppressWarnings("serial")

public class Display_Informacao extends JPanel implements MemoriaObserver {

	private final JLabel label;

	public Display_Informacao() {
		
		Memoria.registerObserver(this);
		 
		
		setBackground(new Color(46, 49, 50));			
		
		label = new JLabel(Memoria.getInfAtual());
		// label = new JLabel(String.valueOf(...); 
		
		label.setForeground(Color.WHITE);

		label.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));

		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

		add(label);
	}

	@Override
	public void novoValor(String val) {
		label.setText(val);
	}
	
	

}
