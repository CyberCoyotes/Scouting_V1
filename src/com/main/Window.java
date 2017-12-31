package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
	JTextField climbRankText = new JTextField("");
	JTextField numRand = new JTextField("");
	JButton clear = new JButton("Delete All Info");
	JButton random = new JButton("Random values");
	JButton blank = new JButton("I'm  i n v i s i b l e");
	
	JTextArea teamShower = new JTextArea("");
	JTextArea scoShower = new JTextArea("");
	JTextArea gearShower = new JTextArea("");
	JTextArea scoAveShower = new JTextArea("");
	JTextArea gearAveShower = new JTextArea("");
	JTextArea climbAveShower = new JTextArea("");
	
	JButton teams = new JButton("Team");
	JButton scores = new JButton("Total points");
	JButton gears = new JButton("Total gears");
	JButton scoAve = new JButton("Average score");
	JButton gearAve = new JButton("Average gears");
	JButton climbAve = new JButton("Average climb");
	
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
		
		Font font = new Font(Font.SERIF, Font.PLAIN, 13);
		random.setFont(font);
		
		add(teamNumField);
		teamNumField.setBounds(5, 5, 120, 20);
		add(gearText);
		gearText.setBounds(5, 55, 120, 20);
		add(teamScore);
		teamScore.setBounds(5, 30, 120, 20);
		add(climbRankText);
		climbRankText.setBounds(5, 80, 120, 20);
		add(submitButton);
		submitButton.setBounds(5, 105, 120, 20);
		submitButton.setBackground(Color.GREEN);
		add(clear);
		clear.setBounds(5, 130, 120, 20);
		clear.setBackground(Color.RED);
		add(random);
		random.setBounds(5, 205, 120, 20);
		add(numRand);
		numRand.setBounds(5, 180, 120, 20);
		
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
		add(climbAve);
		climbAve.setBounds(755, 5, 120, 20);
		climbAve.setBackground(Color.BLUE);
		climbAve.setForeground(Color.WHITE);
		
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
		add(climbAveShower);
		climbAveShower.setBounds(755, 30, 120, 640);
		climbAveShower.setBackground(Color.LIGHT_GRAY);
		climbAveShower.setEditable(false);
		
		teams.addActionListener(this);
		scores.addActionListener(this);
		gears.addActionListener(this);
		scoAve.addActionListener(this);
		gearAve.addActionListener(this);
		climbAve.addActionListener(this);
		
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
			int team = 0, score = 0, gears = 0, climbRank = 0;
			try {
				team = Integer.parseInt(teamNumField.getText());
				score = Integer.parseInt(teamScore.getText());
				gears = Integer.parseInt(gearText.getText());
				climbRank = Integer.parseInt(climbRankText.getText());
			} catch(java.lang.NumberFormatException ex) {
				Warning warning = new Warning("You MUST enter numeric values");
				return;
			}
			
			if(climbRank < 1 || climbRank > 5) {
				Warning warning = new Warning("Climbing rank must be between 1 and 5.");
			} else {
				teamInfo.updateTeam(team, score, gears, climbRank);
				teamNumField.setText("");
				teamScore.setText("");
				gearText.setText("");
				climbRankText.setText("");
			}
			
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
		if(e.getSource() == climbAve) {
			sortSelection = 6;
		}
		if(e.getSource() == random) {
			teamInfo.clear();
			try {
				teamInfo.fillRandom(Integer.parseInt(numRand.getText()));
			} catch(java.lang.NumberFormatException ex) {
				teamInfo.fillRandom(teamInfo.teams.length);
			}
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
		climbAve.setBackground(Color.BLUE);
		climbAve.setForeground(Color.WHITE);
		
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
		case 6:
			climbAve.setBackground(Color.YELLOW);
			climbAve.setForeground(Color.BLACK);
			string = teamInfo.sort(teamInfo.climbAve);
			displayData(string);
			break;
		}
	}
	
	void displayData(String[] string) {
		teamShower.setText(string[0]);
		scoShower.setText(string[1]);
		gearShower.setText(string[2]);
		scoAveShower.setText(string[3]);
		gearAveShower.setText(string[4]);
		climbAveShower.setText(string[5]);
		
	}
	
	private void randomColors() {
		Thread thread = new Thread(() -> {
			while(true) {
				Random randomy = new Random();
				Color myColor = new Color(randomy.nextInt(255), randomy.nextInt(255), randomy.nextInt(255));
				random.setBackground(myColor);
				random.setForeground(myColor.getBlue() < 100 && myColor.getGreen() < 100 && myColor.getRed() < 100 ? Color.WHITE : Color.BLACK);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
