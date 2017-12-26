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
	JTextField gearText = new JTextField("");
	JButton clear = new JButton("Delete All Info");
	JTextArea scoreShower = new JTextArea("Score");
	JButton random = new JButton("Random values");
	JButton blank = new JButton("I'm  i n v i s i b l e");
	
	JRadioButton scoreSort = new JRadioButton("Sort By Score");
	JRadioButton gearSort = new JRadioButton("Sort By Gears");
	JRadioButton teamSort = new JRadioButton("Sort By Team");
	JRadioButton scoAveSort = new JRadioButton("Sort by Ave Score");
	JRadioButton gearAveSort = new JRadioButton("Sort by Ave Gear");
	ButtonGroup sortBy = new ButtonGroup();
	
	int sortSelection = 1;
	
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

		add(clear);
		clear.setBounds(5, 105, 120, 20);
		clear.setBackground(Color.RED);
		
		add(random);
		random.setBounds(5, 130, 120, 20);
		
		add(scoreShower);
		scoreShower.setBounds(255, 5, 450, 640);
		scoreShower.setBackground(Color.LIGHT_GRAY);
		
		add(teamSort);
		teamSort.setBounds(130, 5, 120, 20);
		teamSort.setSelected(true);
		
		add(scoreSort);
		scoreSort.setBounds(130, 30, 120, 20);
		
		add(gearSort);
		gearSort.setBounds(130, 55, 120, 20);
		
		add(scoAveSort);
		scoAveSort.setBounds(130, 80, 120, 20);
		
		add(gearAveSort);
		gearAveSort.setBounds(130, 105, 120, 20);
		
		
		
		submitButton.addActionListener(this);
		clear.addActionListener(this);
		random.addActionListener(this);
		gearSort.addActionListener(this);
		scoreSort.addActionListener(this);
		teamSort.addActionListener(this);
		scoAveSort.addActionListener(this);
		gearAveSort.addActionListener(this);
		
		
		sortBy.add(gearSort);
		sortBy.add(scoreSort);
		sortBy.add(teamSort);
		sortBy.add(scoAveSort);
		sortBy.add(gearAveSort);
		
		scoreShower.setVisible(true);
		scoreShower.setEditable(false);
		add(blank);
		blank.setVisible(false);
		
		String[] string = teamInfo.sort(teamInfo.teams);
		displayData(string);
		
		Thread thread = new Thread(() -> {
			while(true) {
				random.setBackground(Color.red);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				random.setBackground(Color.green);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				random.setBackground(Color.cyan);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				random.setBackground(Color.orange);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				random.setBackground(Color.magenta);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	@SuppressWarnings("unused")
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
			}
			teamNumField.setText("");
			teamScore.setText("");
			gearText.setText("");
		}
		if(e.getSource() == clear) {
			teamInfo.clear();
			scoreShower.setText("");
		}
		
		if(e.getSource() == teamSort) {
			sortSelection = 1;
		}
		if(e.getSource() == scoreSort) {
			sortSelection = 2;
		}
		if(e.getSource() == gearSort) {
			sortSelection = 3;
		}
		if(e.getSource() == scoAveSort) {
			sortSelection = 4;
		}
		if(e.getSource() == gearAveSort) {
			sortSelection = 5;
		}
		
		if(e.getSource() == random) {
			teamInfo.clear();
			teamInfo.fillRandom();
		}
		
		sort();
		teamInfo.save();
	}
	
	void sort() {
		String[] string;
		switch(sortSelection) {
		case 1:
			string = teamInfo.sort(teamInfo.teams);
			displayData(string);
			break;
		case 2:
			string = teamInfo.sort(teamInfo.scores);
			displayData(string);
			break;
		case 3:
			string = teamInfo.sort(teamInfo.gears);
			displayData(string);
			break;
		case 4:
			string = teamInfo.sort(teamInfo.scoAve);
			displayData(string);
			break;
		case 5:
			string = teamInfo.sort(teamInfo.gearAve);
			displayData(string);
			break;
		}
	}
	
	void displayData(String[] string) {
		String bigString = "";
		for(int i = 0; i < teamInfo.teams.length; i++) {
			if(string[i] != null) {
				bigString = bigString + string[i];
			}
		}
		scoreShower.setText(bigString);
	}
}
