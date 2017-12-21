package com.main;

import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener {
	Teams teamInfo = new Teams();
	
	private static final long serialVersionUID = 1L;
	
	JButton submitButton = new JButton("Submit");
	JTextField teamNumField = new JTextField("Team number");
	JTextField teamScore = new JTextField("Team score");
	JTextField ts = new JTextField("Enter");
	JTextField gearText = new JTextField("Num Gears");
	JButton showScore = new JButton("Show score");
	JButton sort = new JButton("Sort");
	JButton clear = new JButton("Clear All Info");
	JTextArea scoreShower = new JTextArea("Score");
	
	public Window(int x, int y) {
		super("3603 FRC Scouting");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
		this.setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(new FlowLayout());
		
		add(teamNumField);
		teamNumField.setBounds(5, 5, 120, 20);
		
		add(gearText);
		gearText.setBounds(5, 55, 120, 20);
		
		add(teamScore);
		teamScore.setBounds(5, 30, 120, 20);
		
		add(submitButton);
		submitButton.setBounds(5, 80, 120, 20);
		
		add(ts);
		ts.setBounds(130, 5, 120, 20);
		//
		add(showScore);
		showScore.setBounds(130, 30, 120, 20);
		
		add(sort);
		sort.setBounds(130, 55, 120, 20);
		
		add(clear);
		clear.setBounds(5, 105, 120, 20);
		
		add(scoreShower);
		scoreShower.setBounds(130, 105, 120, 800);
		scoreShower.setVisible(false);
		
		//scoreShower.setText("");
		teamInfo.load();
		
		submitButton.addActionListener(this);
		showScore.addActionListener(this);
		sort.addActionListener(this);
		clear.addActionListener(this);
		
		//scoreShower.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitButton) {
			String team = teamNumField.getText();
			String score = teamScore.getText();
			String gears = gearText.getText();
			try {
				teamInfo.updateTeam(Integer.parseInt(team), Integer.parseInt(score), Integer.parseInt(gears));
			} catch(java.lang.NumberFormatException ex) {
				Warning warning = new Warning("You MUST enter numeric values");
				warning.setVisible(true);
			}
			teamNumField.setText("");
			teamScore.setText("");
			gearText.setText("");
			teamInfo.save();
		}
		if(e.getSource() == showScore) {
			try {
				int team = Integer.parseInt(ts.getText());
				String score = teamInfo.getTeamScore(team);
				scoreShower.setVisible(true);
				scoreShower.setText(score);
			} catch(java.lang.NumberFormatException ex) {
				Warning warning = new Warning("You MUST enter numeric values");
				warning.setVisible(true);
			}
			ts.setText("");
		}
		if(e.getSource() == sort) {
			String[] string = teamInfo.sort();
			teamInfo.save();
			String bigString = "";
			for(int i = 0; i < 40; i++) {
				bigString = string[i] + "\n";
			}
			scoreShower.setText(bigString);
			scoreShower.setVisible(true);
			
		}
		if(e.getSource() == clear) {
			teamInfo.clear();
		}
	}
}
