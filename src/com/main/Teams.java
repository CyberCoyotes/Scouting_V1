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
	int numMat[] = new int[40];
	double scoAve[] = new double[40];
	double gearAve[] = new double[40];
	int gears[] = new int[40];
	int amount;
	
	public Teams() {
	}
	
	void doAverages() {
		for(int i = 0; i < teams.length; i++) {
			if(numMat[i] != 0) {
				scoAve[i] = scores[i] / numMat[i];
				gearAve[i] = gears[i] / numMat[i];
			}
		}
	}
	
	void updateTeam(int teamNumber, int score, int gear) {
		
		int value = 0;
		boolean found = false;
		
		for(int i = 0; i < teams.length; i++) {
			if(teams[i] == teamNumber && !found) {
				value = i;
				found = true;
			}
		}
		
		if(found) {
			int oldScore = scores[value];
			int oldGear = gears[value];
			scores[value] = oldScore + score;
			gears[value] = oldGear + gear;
			numMat[value]++;
		} else {
			addTeam(teamNumber, score, gear);
		}
		doAverages();
	}
	
	void addTeam(int teamNumber, int score, int gear) {
		try {
			int value = 0;
			boolean emptySlot = false;
			for(int i = 0; i < teams.length; i++) {
				if(teams[i] == 0 && !emptySlot) {
					value = i;
					emptySlot = true;
				}
			}
			
			if(emptySlot) {
				teams[value] = teamNumber;
				scores[value] = score;
				gears[value] = gear;
				numMat[value]++;
			} else {
				@SuppressWarnings("unused")
				Warning warning = new Warning("Too many teams. The maximum number of teams is " + teams.length);
			}
			
		} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
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
        int temp4 = 0;
        double temp5 = 0;
        double temp6 = 0;
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++){  
		        if(gears[j-1] < gears[j]){  
			        temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = gears[j-1];
			        temp4 = numMat[j-1];
			        temp5 = scoAve[j-1];
			        temp6 = gearAve[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        numMat[j-1] = numMat[j];
			        scoAve[j-1] = scoAve[j];
			        gearAve[j-1] = gearAve[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
			        numMat[j] = temp4;
			        scoAve[j] = temp5;
			        gearAve[j] = temp6;
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
        int temp4 = 0;
        double temp5 = 0;
        double temp6 = 0;
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++){  
		        if(teams[j-1] > teams[j]){  
		        	temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = gears[j-1];
			        temp4 = numMat[j-1];
			        temp5 = scoAve[j-1];
			        temp6 = gearAve[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        numMat[j-1] = numMat[j];
			        scoAve[j-1] = scoAve[j];
			        gearAve[j-1] = gearAve[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
			        numMat[j] = temp4;
			        scoAve[j] = temp5;
			        gearAve[j] = temp6;
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
        int temp4 = 0;
        double temp5 = 0;
        double temp6 = 0;
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++){  
		        if(scores[j-1] < scores[j]){  
		        	temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = gears[j-1];
			        temp4 = numMat[j-1];
			        temp5 = scoAve[j-1];
			        temp6 = gearAve[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        numMat[j-1] = numMat[j];
			        scoAve[j-1] = scoAve[j];
			        gearAve[j-1] = gearAve[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
			        numMat[j] = temp4;
			        scoAve[j] = temp5;
			        gearAve[j] = temp6;
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
		if(value == teams.length-1) {
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
			FileWriter scoreWriter1 = new FileWriter("scores.txt");
			FileWriter gearWriter1 = new FileWriter("gears.txt");
			FileWriter numberMatches1 = new FileWriter("numMat.txt");
			FileWriter scoAve1 = new FileWriter("scoAve.txt");
			FileWriter gearAve1 = new FileWriter("gearAve.txt");
			
			PrintWriter teamWriter2 = new PrintWriter(teamWriter1);
			PrintWriter scoreWriter2 = new PrintWriter(scoreWriter1);
			PrintWriter gearWriter2 = new PrintWriter(gearWriter1);			
			PrintWriter numberMatches2 = new PrintWriter(numberMatches1);			
			PrintWriter scoAve2 = new PrintWriter(scoAve1);			
			PrintWriter gearAve2 = new PrintWriter(gearAve1);
			
			for(int i = 0; i < teams.length; i++) {
				teamWriter2.println(teams[i]);
				scoreWriter2.println(scores[i]);
				gearWriter2.println(gears[i]);
				numberMatches2.println(numMat[i]);
				scoAve2.println(scoAve[i]);
				gearAve2.println(gearAve[i]);
			}
			
			teamWriter2.close();
			scoreWriter2.close();
			gearWriter2.close();
			numberMatches2.close();
			scoAve2.close();
			gearAve2.close();
			
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
			FileReader scoreReader1 = new FileReader("scores.txt");
			FileReader gearReader1 = new FileReader("gears.txt");
			FileReader numberMatches1 = new FileReader("numMat.txt");
			FileReader scoAve1 = new FileReader("scoAve.txt");
			FileReader gearAve1 = new FileReader("gearAve.txt");
			
			BufferedReader teamReader2 = new BufferedReader(teamReader1);
			BufferedReader scoreReader2 = new BufferedReader(scoreReader1);
			BufferedReader gearReader2 = new BufferedReader(gearReader1);
			BufferedReader numberMatches2 = new BufferedReader(numberMatches1);
			BufferedReader scoAve2 = new BufferedReader(scoAve1);
			BufferedReader gearAve2 = new BufferedReader(gearAve1);
			
			for(int i = 0; i < teams.length; i++) {
				teams[i] = Integer.parseInt(teamReader2.readLine());
				scores[i] = Integer.parseInt(scoreReader2.readLine());
				gears[i] = Integer.parseInt(gearReader2.readLine());
				numMat[i] = Integer.parseInt(numberMatches2.readLine());
				scoAve[i] = Double.parseDouble(scoAve2.readLine());
				gearAve[i] = Double.parseDouble(gearAve2.readLine());
			}
			
		} catch(IOException ex) {
			out.println("ERRROR!");
		} catch(java.lang.NumberFormatException ex) {
			Warning warning = new Warning("Invalid Number Format. Check the text file for non-number data");
			warning.setVisible(true);
		}
	}
}