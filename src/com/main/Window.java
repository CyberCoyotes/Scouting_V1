package com.main;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	JButton random = new JButton("Random values");
	JButton blank = new JButton("I'm  i n v i s i b l e");
	
	JTextArea teamShower = new JTextArea("");
	JTextArea scoShower = new JTextArea("");
	JTextArea gearShower = new JTextArea("");
	JTextArea scoAveShower = new JTextArea("");
	JTextArea gearAveShower = new JTextArea("");
	
	JButton teams = new JButton("Team");
	JButton scores = new JButton("Total points");
	JButton gears = new JButton("Total gears");
	JButton scoAve = new JButton("Average score");
	JButton gearAve = new JButton("Average gears");
	
	int sortSelection = 1;
	
	public Window(int x, int y) {
		super("3603 FRC Scouting");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
		this.setSize(x, y);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				teamInfo.save();
				System.exit(0);
			}
		});
		
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
		
		add(teams);
		teams.setBounds(130, 5, 120, 20);
		teams.setBackground(Color.YELLOW);
		teams.setForeground(Color.BLACK);
		add(scores);
		scores.setBounds(255, 5, 120, 20);
		scores.setBackground(Color.BLUE);
		scores.setForeground(Color.WHITE);
		add(gears);
		gears.setBounds(380, 5, 120, 20);
		gears.setBackground(Color.BLUE);
		gears.setForeground(Color.WHITE);
		add(scoAve);
		scoAve.setBounds(505, 5, 120, 20);
		scoAve.setBackground(Color.BLUE);
		scoAve.setForeground(Color.WHITE);
		add(gearAve);
		gearAve.setBounds(630, 5, 120, 20);
		gearAve.setBackground(Color.BLUE);
		gearAve.setForeground(Color.WHITE);
		
		add(teamShower);
		teamShower.setBounds(130, 30, 120, 640);
		teamShower.setBackground(Color.LIGHT_GRAY);
		teamShower.setEditable(false);
		add(scoShower);
		scoShower.setBounds(255, 30, 120, 640);
		scoShower.setBackground(Color.LIGHT_GRAY);
		scoShower.setEditable(false);
		add(gearShower);
		gearShower.setBounds(380, 30, 120, 640);
		gearShower.setBackground(Color.LIGHT_GRAY);
		gearShower.setEditable(false);
		add(scoAveShower);
		scoAveShower.setBounds(505, 30, 120, 640);
		scoAveShower.setBackground(Color.LIGHT_GRAY);
		scoAveShower.setEditable(false);
		add(gearAveShower);
		gearAveShower.setBounds(630, 30, 120, 640);
		gearAveShower.setBackground(Color.LIGHT_GRAY);
		gearAveShower.setEditable(false);
		
		teams.addActionListener(this);
		scores.addActionListener(this);
		gears.addActionListener(this);
		scoAve.addActionListener(this);
		gearAve.addActionListener(this);
		
		submitButton.addActionListener(this);
		clear.addActionListener(this);
		random.addActionListener(this);
		
		add(blank);
		blank.setVisible(false);
		
		String[] string = teamInfo.sort(teamInfo.teams);
		displayData(string);
		randomColors();
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
		}
		
		if(e.getSource() == teams) {
			sortSelection = 1;
		}
		if(e.getSource() == scores) {
			sortSelection = 2;
		}
		if(e.getSource() == gears) {
			sortSelection = 3;
		}
		if(e.getSource() == scoAve) {
			sortSelection = 4;
		}
		if(e.getSource() == gearAve) {
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
		
		teams.setBackground(Color.BLUE);
		teams.setForeground(Color.WHITE);
		scores.setBackground(Color.BLUE);
		scores.setForeground(Color.WHITE);
		gears.setBackground(Color.BLUE);
		gears.setForeground(Color.WHITE);
		scoAve.setBackground(Color.BLUE);
		scoAve.setForeground(Color.WHITE);
		gearAve.setBackground(Color.BLUE);
		gearAve.setForeground(Color.WHITE);
		
		switch(sortSelection) {
		case 1:
			teams.setBackground(Color.YELLOW);
			teams.setForeground(Color.BLACK);
			string = teamInfo.sort(teamInfo.teams);
			displayData(string);
			break;
		case 2:
			scores.setBackground(Color.YELLOW);
			scores.setForeground(Color.BLACK);
			string = teamInfo.sort(teamInfo.scores);
			displayData(string);
			break;
		case 3:
			gears.setBackground(Color.YELLOW);
			gears.setForeground(Color.BLACK);
			string = teamInfo.sort(teamInfo.gears);
			displayData(string);
			break;
		case 4:
			scoAve.setBackground(Color.YELLOW);
			scoAve.setForeground(Color.BLACK);
			string = teamInfo.sort(teamInfo.scoAve);
			displayData(string);
			break;
		case 5:
			gearAve.setBackground(Color.YELLOW);
			gearAve.setForeground(Color.BLACK);
			string = teamInfo.sort(teamInfo.gearAve);
			displayData(string);
			break;
		}
	}
	
	void displayData(String[] string) {
		int x = teamInfo.teams.length;
		String bigString = "";
		for(int i = 0; i < x; i++) {
			if(string[i] != null) {
				bigString = bigString + string[i];
			}
		}
		teamShower.setText(bigString);
		
		bigString = "";
		for(int i = x; i < x*2; i++) {
			if(string[i] != null) {
				bigString = bigString + string[i];
			}
		}
		scoShower.setText(bigString);
		
		bigString = "";
		for(int i = x*2; i < x*3; i++) {
			if(string[i] != null) {
				bigString = bigString + string[i];
			}
		}
		gearShower.setText(bigString);
		
		bigString = "";
		for(int i = x*3; i < x*4; i++) {
			if(string[i] != null) {
				bigString = bigString + string[i];
			}
		}
		scoAveShower.setText(bigString);
		
		bigString = "";
		for(int i = x*4; i < x*5; i++) {
			if(string[i] != null) {
				bigString = bigString + string[i];
			}
		}
		gearAveShower.setText(bigString);
	}
	
	private void randomColors() {
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
}
