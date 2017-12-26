package com.main;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener {
	Teams teamInfo = new Teams();
	
	private static final long serialVersionUID = 1L;
	
	JButton submitButton = new JButton("Submit");
	JTextField teamNumField = new JTextField("");
	JTextField teamScore = new JTextField("");
	JTextField ts = new JTextField("Check a Score");
	JTextField gearText = new JTextField("");
	JButton showScore = new JButton("Show score");
	JButton sort = new JButton("Sort");
	JButton clear = new JButton("Delete All Info");
	JTextArea scoreShower = new JTextArea("Score");
	JButton blank = new JButton("I'm  i n v i s i b l e");
	
	JRadioButton scoreSort = new JRadioButton("Sort By Score");
	JRadioButton gearSort = new JRadioButton("Sort By Gears");
	JRadioButton teamSort = new JRadioButton("Sort By Team");
	ButtonGroup sortBy = new ButtonGroup();
	
	int sortSelection = 3;
	
	public Window(int x, int y) {
		super("3603 FRC Scouting");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
		this.setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(teamNumField);
		teamNumField.setBounds(5, 5, 120, 20);
		
		add(gearText);
		gearText.setBounds(5, 55, 120, 20);
		
		add(teamScore);
		teamScore.setBounds(5, 30, 120, 20);
		
		add(submitButton);
		submitButton.setBounds(5, 80, 120, 20);
		submitButton.setBackground(Color.GREEN);
		
		add(ts);
		ts.setBounds(130, 5, 120, 20);
		
		add(showScore);
		showScore.setBounds(130, 30, 120, 20);
		showScore.setBackground(Color.magenta);
		
		add(sort);
		sort.setBounds(130, 130, 120, 20);
		sort.setBackground(Color.CYAN);

		add(clear);
		clear.setBounds(5, 105, 120, 20);
		clear.setBackground(Color.RED);
		
		add(scoreShower);
		scoreShower.setBounds(255, 5, 180, 640);
		scoreShower.setBackground(Color.LIGHT_GRAY);
		
		add(gearSort);
		gearSort.setBounds(130, 105, 120, 20);
		
		add(scoreSort);
		scoreSort.setBounds(130, 80, 120, 20);
		
		add(teamSort);
		teamSort.setBounds(130, 55, 120, 20);
		teamSort.setSelected(true);
		
		scoreShower.setText("");
		teamInfo.load();
		
		submitButton.addActionListener(this);
		showScore.addActionListener(this);
		sort.addActionListener(this);
		clear.addActionListener(this);
		gearSort.addActionListener(this);
		scoreSort.addActionListener(this);
		teamSort.addActionListener(this);
		
		sortBy.add(gearSort);
		sortBy.add(scoreSort);
		sortBy.add(teamSort);
		
		scoreShower.setVisible(true);
		scoreShower.setEditable(false);
		add(blank);
		blank.setVisible(false);
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
		}
		if(e.getSource() == sort) {
			switch(sortSelection) {
			case 0:
				Warning warning = new Warning("You must select a sort mode");
				warning.setVisible(true);
				break;
			case 1:
				{
					String[] string = teamInfo.sortByScore();
					String bigString = "";
					for(int i = 0; i < teamInfo.scores.length; i++) {
						if(string[i] != null) {
							bigString = bigString + string[i];
						}
					}
					scoreShower.setText(bigString);
				}
				break;
			case 2:
				{
					String[] string = teamInfo.sortByGears();
					String bigString = "";
					for(int i = 0; i < teamInfo.gears.length; i++) {
						if(string[i] != null) {
							bigString = bigString + string[i];
						}
					}
					scoreShower.setText(bigString);
				}
				break;
			case 3:
				{
					String[] string = teamInfo.sortByTeams();
					String bigString = "";
					for(int i = 0; i < teamInfo.teams.length; i++) {
						if(string[i] != null) {
							bigString = bigString + string[i];
						}
					}
					scoreShower.setText(bigString);
				}
				break;
			}
		}
		if(e.getSource() == gearSort) {
			sortSelection = 2;
		}
		if(e.getSource() == clear) {
			teamInfo.clear();
		}
		if(e.getSource() == scoreSort) {
			sortSelection = 1;
		}
		if(e.getSource() == teamSort) {
			sortSelection = 3;
		}
		teamInfo.save();
	}
}
