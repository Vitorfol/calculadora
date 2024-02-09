package com.java.vitor.calc.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.java.vitor.calc.modelo.Memoria;

@SuppressWarnings("serial")

public class Botao extends JButton implements ActionListener {

	public Botao(String label, Color cor) {

		setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		setOpaque(true);
		setText(label);
		setBackground(cor);
		setForeground(Color.white);
		setBorder(BorderFactory.createLineBorder(new Color(46, 49, 50)));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		String click = this.getText();
		if (click == ",") {
			click = ".";
		}
		Memoria.novoComando(click);
	}

}
