package com.main;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Warning extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Warning(String message) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(500, 100);
		setAlwaysOnTop(true);
		JLabel warn = new JLabel(message);
		add(warn);
	}
}
