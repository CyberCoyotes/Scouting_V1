package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Window extends JFrame implements ActionListener {
	
	Teams teamInfo = new Teams();
	
	int showerHeight = 685;
	
	private static final long serialVersionUID = 1L;
	
	JTextField teamInput = new JTextField("");
	JTextField scoreInput = new JTextField("");
	JTextField gearInput = new JTextField("");
	JTextField climbInput = new JTextField("");
	JTextField randomInput = new JTextField("");
	
	JButton submitButton = new JButton("Submit");
	JButton clearButton = new JButton("Delete All Info");
	JButton randomButton = new JButton("Random values");
	JToggleButton showTeamButton = new JToggleButton("Show team");
	JButton blank = new JButton("I'm  i n v i s i b l e");
	
	JEditorPane teamShower = new JEditorPane("text/html", "");
	JEditorPane scoreShower = new JEditorPane("text/html", "");
	JEditorPane gearShower = new JEditorPane("text/html", "");
	JEditorPane aveScoreShower = new JEditorPane("text/html", "");
	JEditorPane aveGearShower = new JEditorPane("text/html", "");
	JEditorPane aveClimbShower = new JEditorPane("text/html", "");
	
	JButton teamSort = new JButton("Team");
	JButton scoreSort = new JButton("Total points");
	JButton gearSort = new JButton("Total gears");
	JButton aveScoreSort = new JButton("Average score");
	JButton aveGearSort = new JButton("Average gears");
	JButton aveClimbSort = new JButton("Average climb");
	
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
		randomButton.setFont(font);
		
		add(teamInput);
		teamInput.setBounds(5, 5, 120, 20);
		add(gearInput);
		gearInput.setBounds(5, 55, 120, 20);
		add(scoreInput);
		scoreInput.setBounds(5, 30, 120, 20);
		add(climbInput);
		climbInput.setBounds(5, 80, 120, 20);
		add(submitButton);
		submitButton.setBounds(5, 105, 120, 20);
		submitButton.setBackground(Color.GREEN);
		add(showTeamButton);
		showTeamButton.setBounds(5, 130, 120, 20);
		showTeamButton.setBackground(Color.CYAN);
		add(clearButton);
		clearButton.setBounds(5, 155, 120, 20);
		clearButton.setBackground(Color.RED);
		add(randomButton);
		randomButton.setBounds(5, 230, 120, 20);
		add(randomInput);
		randomInput.setBounds(5, 205, 120, 20);
		
		add(teamSort);
		teamSort.setBounds(130, 5, 120, 20);
		teamSort.setBackground(Color.YELLOW);
		teamSort.setForeground(Color.BLACK);
		add(scoreSort);
		scoreSort.setBounds(255, 5, 120, 20);
		scoreSort.setBackground(Color.BLUE);
		scoreSort.setForeground(Color.WHITE);
		add(gearSort);
		gearSort.setBounds(380, 5, 120, 20);
		gearSort.setBackground(Color.BLUE);
		gearSort.setForeground(Color.WHITE);
		add(aveScoreSort);
		aveScoreSort.setBounds(505, 5, 120, 20);
		aveScoreSort.setBackground(Color.BLUE);
		aveScoreSort.setForeground(Color.WHITE);
		add(aveGearSort);
		aveGearSort.setBounds(630, 5, 120, 20);
		aveGearSort.setBackground(Color.BLUE);
		aveGearSort.setForeground(Color.WHITE);
		add(aveClimbSort);
		aveClimbSort.setBounds(755, 5, 120, 20);
		aveClimbSort.setBackground(Color.BLUE);
		aveClimbSort.setForeground(Color.WHITE);
		
		add(teamShower);
		teamShower.setBounds(130, 30, 120, showerHeight);
		teamShower.setBackground(Color.LIGHT_GRAY);
		teamShower.setEditable(false);
		add(scoreShower);
		scoreShower.setBounds(255, 30, 120, showerHeight);
		scoreShower.setBackground(Color.LIGHT_GRAY);
		scoreShower.setEditable(false);
		add(gearShower);
		gearShower.setBounds(380, 30, 120, showerHeight);
		gearShower.setBackground(Color.LIGHT_GRAY);
		gearShower.setEditable(false);
		add(aveScoreShower);
		aveScoreShower.setBounds(505, 30, 120, showerHeight);
		aveScoreShower.setBackground(Color.LIGHT_GRAY);
		aveScoreShower.setEditable(false);
		add(aveGearShower);
		aveGearShower.setBounds(630, 30, 120, showerHeight);
		aveGearShower.setBackground(Color.LIGHT_GRAY);
		aveGearShower.setEditable(false);
		add(aveClimbShower);
		aveClimbShower.setBounds(755, 30, 120, showerHeight);
		aveClimbShower.setBackground(Color.LIGHT_GRAY);
		aveClimbShower.setEditable(false);
		
		teamSort.addActionListener(this);
		scoreSort.addActionListener(this);
		gearSort.addActionListener(this);
		aveScoreSort.addActionListener(this);
		aveGearSort.addActionListener(this);
		aveClimbSort.addActionListener(this);
		
		submitButton.addActionListener(this);
		showTeamButton.addActionListener(this);
		clearButton.addActionListener(this);
		randomButton.addActionListener(this);
		
		add(blank);
		blank.setVisible(false);
		
		String[] sortOutput = teamInfo.sort(teamInfo.teams);
		displayData(sortOutput);
		randomColors();
	}

	

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitButton) {
			int team = 0, score = 0, gears = 0, climbRank = 0;
			try {
				team = Integer.parseInt(teamInput.getText());
				score = Integer.parseInt(scoreInput.getText());
				gears = Integer.parseInt(gearInput.getText());
				climbRank = Integer.parseInt(climbInput.getText());
			} catch(java.lang.NumberFormatException ex) {
				Warning warning = new Warning("You MUST enter numeric values");
				return;
			}
			
			if(climbRank < 1 || climbRank > 5) {
				Warning warning = new Warning("Climbing rank must be between 1 and 5.");
			} else {
				teamInfo.updateTeam(team, score, gears, climbRank);
				teamInput.setText("");
				scoreInput.setText("");
				gearInput.setText("");
				climbInput.setText("");
			}
			
		}
		if(e.getSource() == clearButton) {
			teamInfo.clear();
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
		if(e.getSource() == aveScoreSort) {
			sortSelection = 4;
		}
		if(e.getSource() == aveGearSort) {
			sortSelection = 5;
		}
		if(e.getSource() == aveClimbSort) {
			sortSelection = 6;
		}
		if(e.getSource() == randomButton) {
			teamInfo.clear();
			try {
				teamInfo.fillRandom(Integer.parseInt(randomInput.getText()));
			} catch(java.lang.NumberFormatException ex) {
				teamInfo.fillRandom(teamInfo.teams.length);
			}
		}
		if(e.getSource() == showTeamButton) {
			if(showTeamButton.isSelected()) {
				int team = 0;
				try {
					team = Integer.parseInt(teamInput.getText());
				} catch(java.lang.NumberFormatException ex) {
					Warning warning = new Warning("You MUST enter numeric values");
					return;
				}
				
				teamInfo.bold(true, team);
			} else {
				teamInfo.bold(false, 0);
			}
		}
		sort();
		teamInfo.save();
	}
	
	void sort() {
		String[] sortOutput;
		
		teamSort.setBackground(Color.BLUE);
		teamSort.setForeground(Color.WHITE);
		scoreSort.setBackground(Color.BLUE);
		scoreSort.setForeground(Color.WHITE);
		gearSort.setBackground(Color.BLUE);
		gearSort.setForeground(Color.WHITE);
		aveScoreSort.setBackground(Color.BLUE);
		aveScoreSort.setForeground(Color.WHITE);
		aveGearSort.setBackground(Color.BLUE);
		aveGearSort.setForeground(Color.WHITE);
		aveClimbSort.setBackground(Color.BLUE);
		aveClimbSort.setForeground(Color.WHITE);
		
		switch(sortSelection) {
		case 1:
			teamSort.setBackground(Color.YELLOW);
			teamSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.teams);
			displayData(sortOutput);
			break;
		case 2:
			scoreSort.setBackground(Color.YELLOW);
			scoreSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.scores);
			displayData(sortOutput);
			break;
		case 3:
			gearSort.setBackground(Color.YELLOW);
			gearSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.gears);
			displayData(sortOutput);
			break;
		case 4:
			aveScoreSort.setBackground(Color.YELLOW);
			aveScoreSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.scoAve);
			displayData(sortOutput);
			break;
		case 5:
			aveGearSort.setBackground(Color.YELLOW);
			aveGearSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.gearAve);
			displayData(sortOutput);
			break;
		case 6:
			aveClimbSort.setBackground(Color.YELLOW);
			aveClimbSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.climbAve);
			displayData(sortOutput);
			break;
		}
	}
	
	void displayData(String[] sortData) {
		teamShower.setText(sortData[0]);
		scoreShower.setText(sortData[1]);
		gearShower.setText(sortData[2]);
		aveScoreShower.setText(sortData[3]);
		aveGearShower.setText(sortData[4]);
		aveClimbShower.setText(sortData[5]);
		
	}
	
	private void randomColors() {
		Thread thread = new Thread(() -> {
			while(true) {
				Random random = new Random();
				Color myColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
				randomButton.setBackground(myColor);
				randomButton.setForeground(myColor.getBlue() < 100 && myColor.getGreen() < 100 && myColor.getRed() < 100 ? Color.WHITE : Color.BLACK);
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
