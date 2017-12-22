package com.main;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Teams {
	
	int teams[] = new int[40];
	int scores[] = new int[40];
	int gears[] = new int[40];
	int amount;
	
	public Teams() {
	}
	
	void updateTeam(int teamNumber, int score, int gear) {
		
		int value = 0;
		boolean notFound = false;
		try {
			while(teams[value] != teamNumber && !notFound) {
				value++;
				if(value>39) {
					notFound = true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			Warning warning = new Warning("Too many teams. The maximum is 40 teams");
			warning.setVisible(true);
		}
		if(notFound) {
			addTeam(teamNumber, score, gear);
		} else {
			int oldScore = scores[value];
			int oldGear = gears[value];
			scores[value] = oldScore + score;
			gears[value] = oldGear + gear;
		}
	}
	
	void addTeam(int teamNumber, int score, int gear) {
		try {
			int value = 0;
			boolean flag = false;
			while(teams[value] != 0 && value <= teams.length) {
				value++;
			}
			
			if(flag) {
				teams[value] = teamNumber;
				scores[value] = score;
				gears[value] = gear;
			}
			Warning warning = new Warning(Integer.toString(teams[value]));
			warning.setVisible(true);
		} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
			
		}
	}
	
	void removeTeam(int teamNumber) {
		int value = 0;
		while(teams[value] != teamNumber && value < teams.length) {
			value++;
		}
		
		if(teams[value] == teamNumber) {
			teams[value] = 0;
			scores[value] = 0;
			gears[value] = 0;
		} else {
			Warning teamNotFound = new Warning("Team not found: " + teamNumber);
			teamNotFound.setVisible(true);
		}
	}
	
	String[] sortByGears() {
		int n = gears.length;  
        int temp = 0;
        int temp2 = 0;
        int temp3 = 0;
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++){  
		        if(gears[j-1] < gears[j]){  
			        temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = gears[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
		        }  
	        }  
        }
        String[] string = new String[40];
        for(int i = 0; i < n; i++) {
        	if(teams[i] != 0) {
        		String s = "Team " + teams[i] + " score " + scores[i] + " gears " + gears [i];
        		string[i] = s + "\n";
        	}
        }
        return string;
	}
	
	String[] sortByTeams() {
		int n = teams.length;  
        int temp = 0;
        int temp2 = 0;
        int temp3 = 0;
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++){  
		        if(teams[j-1] > teams[j]){  
			        temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = gears[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
		        }  
	        }  
        }
        String[] string = new String[40];
        for(int i = 0; i < n; i++) {
        	if(teams[i] != 0) {
        		String s = "Team " + teams[i] + " score " + scores[i] + " gears " + gears [i];
        		string[i] = s + "\n";
        	}
        }
        return string;
	}
	
	String[] sortByScore() {
		int n = scores.length;  
        int temp = 0;
        int temp2 = 0;
        int temp3 = 0;
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++){  
		        if(scores[j-1] < scores[j]){  
			        temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = gears[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
		        }  
	        }  
        }
        String[] string = new String[40];
        for(int i = 0; i < n; i++) {
        	if(teams[i] != 0) {
        		String s = "Team " + teams[i] + " score " + scores[i] + " gears " + gears [i];
        		string[i] = s + "\n";
        	}
        }
        return string;
	}
	
	String getTeamScore(int teamNumber) {
		int value = 0;
		while(teams[value] != teamNumber && value < teams.length-1) {
			value++;
		}
		if(value == 39) {
			Warning teamNotFound = new Warning("Team not found: " + teamNumber);
			teamNotFound.setVisible(true);
			return "";
		} else {
			String s = "Score: " + scores[value] + " Gears: " + gears[value];
			return s;
		}
	}
	
	void save() {
		try {
			FileWriter teamWriter1 = new FileWriter("teams.txt");
			PrintWriter teamWriter2 = new PrintWriter(teamWriter1);
			
			for(int i = 0; i < teams.length; i++) {
				teamWriter2.println(teams[i]);
			}
			teamWriter1.close();
			//
			FileWriter scoreWriter1 = new FileWriter("scores.txt");
			PrintWriter scoreWriter2 = new PrintWriter(scoreWriter1);
			
			for(int i = 0; i < scores.length; i++) {
				scoreWriter2.println(scores[i]);
			}
			scoreWriter1.close();
			//
			FileWriter gearWriter1 = new FileWriter("gears.txt");
			PrintWriter gearWriter2 = new PrintWriter(gearWriter1);
			
			for(int i = 0; i < gears.length; i++) {
				gearWriter2.println(gears[i]);
			}
			gearWriter1.close();
			
			
		} catch(IOException ex) {
			out.println("ERROR!");
		}
	}
	
	void clear() {
		for(int i = 0; i < teams.length; i++) {
			teams[i] = 0;
			scores[i] = 0;
			gears[i] = 0;
		}
		save();
	}
	
	@SuppressWarnings("resource")
	void load() {
		try {
			FileReader teamReader1 = new FileReader("teams.txt");
			BufferedReader teamReader2 = new BufferedReader(teamReader1);
			
			for(int i = 0; i < teams.length; i++) {
				teams[i] = Integer.parseInt(teamReader2.readLine());
			}
			//
			FileReader scoreReader1 = new FileReader("scores.txt");
			BufferedReader scoreReader2 = new BufferedReader(scoreReader1);
			
			for(int i = 0; i < scores.length; i++) {
				scores[i] = Integer.parseInt(scoreReader2.readLine());
			}
			//
			FileReader gearReader1 = new FileReader("gears.txt");
			BufferedReader gearReader2 = new BufferedReader(gearReader1);
			
			for(int i = 0; i < gears.length; i++) {
				gears[i] = Integer.parseInt(gearReader2.readLine());
			}
		} catch(IOException ex) {
			out.println("ERRROR!");
		} catch(java.lang.NumberFormatException ex) {
			Warning warning = new Warning("Invalid Number Format. Check the text file for non-number data");
			warning.setVisible(true);
		}
	}
}