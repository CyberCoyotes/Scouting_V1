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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Window extends JFrame implements ActionListener {
	
	Teams teamInfo = new Teams();
	
	int showerHeight = 685;
	
	private static final long serialVersionUID = 1L;
	
	JTextField teamInput = new JTextField("");
	JTextField scoreInput = new JTextField("");
	JTextField scaleInput = new JTextField("");
	JTextField switchInput = new JTextField("");
	JTextField vaultInput = new JTextField("");
	JTextField showTeamInput = new JTextField("");
	JTextField randomInput = new JTextField("");
	
	JButton submitButton = new JButton("Submit");
	JButton clearButton = new JButton("Delete All Info");
	JButton randomButton = new JButton("Random values");
	JToggleButton showTeamButton = new JToggleButton("Show team");
	JButton blank = new JButton("I'm  i n v i s i b l e");
	
	JEditorPane teamShower = new JEditorPane("text/html", "");
	JEditorPane aveScoreShower = new JEditorPane("text/html", "");
	JEditorPane scaleShower = new JEditorPane("text/html", "");
	JEditorPane switchShower = new JEditorPane("text/html", "");
	JEditorPane vaultShower = new JEditorPane("text/html", "");
	
	JSlider slider = new JSlider();
	
	JButton teamSort = new JButton("Team");
	JButton aveScoreSort = new JButton("Average score");
	JButton scaleSort = new JButton("Average scale");
	JButton switchSort = new JButton("Average switch");
	JButton vaultSort = new JButton("Average vault");
	
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
		
		/*
		@SuppressWarnings("unused")
		Thread thread = new Thread(() -> {
			while(true) {
				System.out.println(getWidth());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		*/
		
		Font font = new Font(Font.SERIF, Font.PLAIN, 13);
		randomButton.setFont(font);
		
		add(teamInput);
		teamInput.setBounds(5, 5, 120, 20);
		add(scoreInput);
		scoreInput.setBounds(5, 30, 120, 20);
		add(scaleInput);
		scaleInput.setBounds(5, 55, 120, 20);
		add(switchInput);
		switchInput.setBounds(5, 80, 120, 20);
		add(vaultInput);
		vaultInput.setBounds(5, 105, 120, 20);
		
		add(submitButton);
		submitButton.setBounds(5, 130, 120, 20);
		submitButton.setBackground(Color.GREEN);
		add(showTeamInput);
		showTeamInput.setBounds(5, 205, 120, 20);
		add(showTeamButton);
		showTeamButton.setBounds(5, 230, 120, 20);
		showTeamButton.setBackground(Color.CYAN);
		add(clearButton);
		clearButton.setBounds(5, 155, 120, 20);
		clearButton.setBackground(Color.RED);
		add(randomButton);
		randomButton.setBounds(5, 695, 120, 20);
		add(randomInput);
		randomInput.setBounds(5, 670, 120, 20);
		
		add(teamSort);
		teamSort.setBounds(130, 5, 120, 20);
		teamSort.setBackground(Color.YELLOW);
		teamSort.setForeground(Color.BLACK);
		add(aveScoreSort);
		aveScoreSort.setBounds(255, 5, 120, 20);
		aveScoreSort.setBackground(Color.BLUE);
		aveScoreSort.setForeground(Color.WHITE);
		add(scaleSort);
		scaleSort.setBounds(380, 5, 120, 20);
		scaleSort.setBackground(Color.BLUE);
		scaleSort.setForeground(Color.WHITE);
		add(switchSort);
		switchSort.setBounds(505, 5, 120, 20);
		switchSort.setBackground(Color.BLUE);
		switchSort.setForeground(Color.WHITE);
		add(vaultSort);
		vaultSort.setBounds(630, 5, 120, 20);
		vaultSort.setBackground(Color.BLUE);
		vaultSort.setForeground(Color.WHITE);
		
		add(teamShower);
		teamShower.setBounds(130, 30, 120, showerHeight);
		teamShower.setBackground(Color.LIGHT_GRAY);
		teamShower.setEditable(false);
		add(aveScoreShower);
		aveScoreShower.setBounds(255, 30, 120, showerHeight);
		aveScoreShower.setBackground(Color.LIGHT_GRAY);
		aveScoreShower.setEditable(false);
		add(scaleShower);
		scaleShower.setBounds(380, 30, 120, showerHeight);
		scaleShower.setBackground(Color.LIGHT_GRAY);
		scaleShower.setEditable(false);
		add(switchShower);
		switchShower.setBounds(505, 30, 120, showerHeight);
		switchShower.setBackground(Color.LIGHT_GRAY);
		switchShower.setEditable(false);
		add(vaultShower);
		vaultShower.setBounds(630, 30, 120, showerHeight);
		vaultShower.setBackground(Color.LIGHT_GRAY);
		vaultShower.setEditable(false);
		
		teamSort.addActionListener(this);
		aveScoreSort.addActionListener(this);
		scaleSort.addActionListener(this);
		switchSort.addActionListener(this);
		vaultSort.addActionListener(this);
		
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
			int team = 0, score = 0, scale = 0, swi = 0, vault = 0;
			try {
				team = Integer.parseInt(teamInput.getText());
				score = Integer.parseInt(scoreInput.getText());
				scale = Integer.parseInt(scaleInput.getText());
				swi = Integer.parseInt(switchInput.getText());
				vault = Integer.parseInt(vaultInput.getText());
				teamInfo.updateTeam(team, score, scale, swi, vault);
			} catch(java.lang.NumberFormatException ex) {
				Warning warning = new Warning("You MUST enter numeric values");
				return;
			}
			teamInput.setText("");
			scoreInput.setText("");
			scaleInput.setText("");
			switchInput.setText("");
			vaultInput.setText("");
		}
		if(e.getSource() == clearButton) {
			teamInfo.clear();
			teamInfo.bold(false, 0);
			showTeamButton.setSelected(false);
			showTeamInput.setText("");
		}
		
		if(e.getSource() == teamSort) {
			sortSelection = 1;
		}
		if(e.getSource() == aveScoreSort) {
			sortSelection = 2;
		}
		if(e.getSource() == scaleSort) {
			sortSelection = 3;
		}
		if(e.getSource() == switchSort) {
			sortSelection = 4;
		}
		if(e.getSource() == vaultSort) {
			sortSelection = 5;
		}
		if(e.getSource() == randomButton) {
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
					team = Integer.parseInt(showTeamInput.getText());
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
		aveScoreSort.setBackground(Color.BLUE);
		aveScoreSort.setForeground(Color.WHITE);
		scaleSort.setBackground(Color.BLUE);
		scaleSort.setForeground(Color.WHITE);
		switchSort.setBackground(Color.BLUE);
		switchSort.setForeground(Color.WHITE);
		vaultSort.setBackground(Color.BLUE);
		vaultSort.setForeground(Color.WHITE);
		
		switch(sortSelection) {
		case 1:
			teamSort.setBackground(Color.YELLOW);
			teamSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.teams);
			displayData(sortOutput);
			break;
		case 2:
			aveScoreSort.setBackground(Color.YELLOW);
			aveScoreSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.scoAve);
			displayData(sortOutput);
			break;
		case 3:
			scaleSort.setBackground(Color.YELLOW);
			scaleSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.scaleAve);
			displayData(sortOutput);
			break;
		case 4:
			switchSort.setBackground(Color.YELLOW);
			switchSort.setForeground(Color.BLACK);
			sortOutput = teamInfo.sort(teamInfo.switchAve);
			displayData(sortOutput);
			break;
		case 5:
			vaultSort.setBackground(Color.YELLOW);
			vaultSort.setForeground(Color.BLACK);
		}
	}
	
	void displayData(String[] sortData) {
		teamShower.setText(sortData[0]);
		aveScoreShower.setText(sortData[1]);
		scaleShower.setText(sortData[2]);
		switchShower.setText(sortData[3]);
		vaultShower.setText(sortData[4]);
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
