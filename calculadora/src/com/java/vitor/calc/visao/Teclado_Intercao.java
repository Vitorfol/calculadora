package com.java.vitor.calc.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")

//242, 163, 60 LARANJA
// 99, 99, 99 CINZA CLARO
// 68, 68, 68 CINZA ESCURO

public class Teclado_Intercao extends JPanel {

	public Teclado_Intercao() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();

		setLayout(layout);

		implementarTecla("AC", new Color(68, 68, 68), constraint, 0, 0);
		implementarTecla("+/-", new Color(68, 68, 68), constraint, 1, 0);
		implementarTecla("%", new Color(68, 68, 68), constraint, 2, 0);
		implementarTecla("÷", new Color(242, 163, 60), constraint, 3, 0);

		implementarTecla("7", new Color(99, 99, 99), constraint, 0, 1);
		implementarTecla("8", new Color(99, 99, 99), constraint, 1, 1);
		implementarTecla("9", new Color(99, 99, 99), constraint, 2, 1);
		implementarTecla("x", new Color(242, 163, 60), constraint, 3, 1);

		implementarTecla("4", new Color(99, 99, 99), constraint, 0, 2);
		implementarTecla("5", new Color(99, 99, 99), constraint, 1, 2);
		implementarTecla("6", new Color(99, 99, 99), constraint, 2, 2);
		implementarTecla("-", new Color(242, 163, 60), constraint, 3, 2);

		implementarTecla("1", new Color(99, 99, 99), constraint, 0, 3);
		implementarTecla("2", new Color(99, 99, 99), constraint, 1, 3);
		implementarTecla("3", new Color(99, 99, 99), constraint, 2, 3);
		implementarTecla("+", new Color(242, 163, 60), constraint, 3, 3);

		implementarTecla("0", new Color(99, 99, 99), constraint, 0, 4);
		implementarTecla(",", new Color(99, 99, 99), constraint, 2, 4);
		implementarTecla("=", new Color(242, 163, 60), constraint, 3, 4);

	}

	private void implementarTecla(String label, Color cor, GridBagConstraints constraint, int x, int y) {

		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.weighty = 1;
		constraint.fill = GridBagConstraints.BOTH;

		if (label == "0") {
			constraint.gridwidth = 2;
		}

		constraint.gridx = x;
		constraint.gridy = y;
		Botao tecla = new Botao(label, cor);
		add(tecla, constraint);
	}

}
