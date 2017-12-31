package com.main;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;

public class Teams {
	
	int highestPossibleTeamNumber = 6999;
	int not = 40; //Number of teams
	
	int teams[] = new int[not]; //Array to store team numbers
	int scores[] = new int[not]; //Array to store scores
	int numMat[] = new int[not]; //Array to store the number of matches played
	double scoAve[] = new double[not]; //Array to store average team scores
	double gearAve[] = new double[not]; //Array to store average gears
	int climbs[] = new int[not]; //Array to store total number of climb points
	double climbAve[] = new double[not]; //Array to store average number of climb points
	int gears[] = new int[not]; //Array to store total number of gears
	
	public Teams() {
		load(); //Reload any saved data
	}
	
	void doAverages() { //Method to calculate averages
		for(int i = 0; i < teams.length; i++) {
			if(numMat[i] != 0) { //To keep from dividing by zero
				scoAve[i] = (double) scores[i] / numMat[i]; //Calculate score averages
				gearAve[i] = (double) gears[i] / numMat[i]; //Calculate gear averages
				climbAve[i] = (double) climbs[i] / numMat[i]; //Calculate climb averages
			}
		}
	}
	
	void updateTeam(int teamNumber, int score, int gear, int climb) { //Method to update team info after a match
		
		int value = 0;//Used to find the position of the requested team number in the team array
		boolean found = false; //Boolean to state if the team number was found in the array or not
		
		for(int i = 0; i < teams.length; i++) { //Cycle through all of the positions
			if(teams[i] == teamNumber && !found) { //If it found a match...
				value = i; //Store the position into value
				found = true; //Flag that the number has been found
			}
		}
		
		if(found) { //If the team already exists...
			scores[value] = scores[value] + score;//Add the new score to their total
			gears[value] = gears[value] + gear; //Add the new gears to their total
			climbs[value] = climbs[value] + climb; //Add the new climb rank to their total
			numMat[value]++; //Increase the number of matches they've played in
		} else { //If the team doesn't already exist...
			addTeam(teamNumber, score, gear, climb); //Add a new team with the requested information
		}
		doAverages(); //Calculate the new averages
	}
	
	void addTeam(int teamNumber, int score, int gear, int climb) { //Method for adding a team
		try {//Try to locate an empty slot. Empty is defined by the team value being 0
			int value = 0; //Used to save the position in the array
			boolean emptySlot = false; //Boolean to state if there is an empty slot or not
			for(int i = 0; i < teams.length; i++) {
				if(teams[i] == 0 && !emptySlot) {//If it found an empty slot...
					value = i;//Store the position into value
					emptySlot = true; //Flag that there is a slot
				}
			}
			
			if(emptySlot) {//If a slot has been found...
				teams[value] = teamNumber;//Save the team number
				scores[value] = score;//Save the score
				gears[value] = gear;//Save the number of gears
				climbs[value] = climb;//Save the climb rank
				numMat[value] = 1;//Set their number of matches to 1
			} else {//If there isn't an empty slot...
				@SuppressWarnings("unused")
				//Warn that there are no slots left
				Warning warning = new Warning("Too many teams. The maximum number of teams is " + teams.length);
			}
			
		} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}
	
	String[] sort(double[] by) {//Method to sort by an average. "by" is short for sort by
		DecimalFormat d = new DecimalFormat("#.00");//Format decimal numbers to only have 2 decimal places
		int n = by.length;  //Save the length of the requested array
        int temp = 0; //Temporary score
        int temp2 = 0; //Temporary team
        int temp3 = 0; //Temporary gear
        int temp4 = 0; //Temporary number of matches
        double temp5 = 0; //Temporary average score
        double temp6 = 0; //Temporary average gear
        int temp7 = 0; //Temporary total climb rank
        double temp8 = 0; //Temporary average climb rank
        
        //This is a bubble sort algorithm for ordering the stats
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++) {
		        if(by[j-1] < by[j]){  
			        temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = gears[j-1];
			        temp4 = numMat[j-1];
			        temp5 = scoAve[j-1];
			        temp6 = gearAve[j-1];
			        temp7 = climbs[j-1];
			        temp8 = climbAve[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        numMat[j-1] = numMat[j];
			        scoAve[j-1] = scoAve[j];
			        gearAve[j-1] = gearAve[j];
			        climbs[j-1] = climbs[j];
			        climbAve[j-1] = climbAve[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
			        numMat[j] = temp4;
			        scoAve[j] = temp5;
			        gearAve[j] = temp6;
			        climbs[j] = temp7;
			        climbAve[j] = temp8;
		        }
	        }  
        }
        
        //Build a string that has all of the sorted data in it. This is used by the score showers.
        String[] string = new String[teams.length*6];
        for(int i = 0; i < n; i++) {
        	if(teams[i] != 0) {
        		String s = teams[i] + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length; i < by.length*2; i++) {
        	if(teams[i - by.length] != 0) {
        		String s = scores[i - (by.length)] + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*2; i < by.length*3; i++) {
        	if(teams[i - by.length*2] != 0) {
        		String s = gears[i - (by.length*2)] + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*3; i < by.length*4; i++) {
        	if(teams[i - by.length*3] != 0) {
        		String s = d.format(scoAve[i - (by.length * 3)]) + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*4; i < by.length*5; i++) {
        	if(teams[i - by.length*4] != 0) {
        		String s = d.format(gearAve[i - (by.length * 4)]) + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*5; i < by.length*6; i++) {
        	if(teams[i - by.length*5] != 0) {
        		String s = d.format(climbAve[i - (by.length * 5)]) + "\n";
        		string[i] = s;
        	}
        }
        return string;
	}
	
	String[] sort(int[] by) {//Same as the other score but uses the integer type
		DecimalFormat d = new DecimalFormat("#.00");
		int n = by.length;  
        int temp = 0;
        int temp2 = 0;
        int temp3 = 0;
        int temp4 = 0;
        double temp5 = 0;
        double temp6 = 0;
        int temp7 = 0;
        double temp8 = 0;
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++) {
	        	if(by == teams) {
			        if(by[j-1] > by[j]){  
			        	temp = scores[j-1];
				        temp2 = teams[j-1];
				        temp3 = gears[j-1];
				        temp4 = numMat[j-1];
				        temp5 = scoAve[j-1];
				        temp6 = gearAve[j-1];
				        temp7 = climbs[j-1];
				        temp8 = climbAve[j-1];
				        
				        scores[j-1] = scores[j];
				        teams[j-1] = teams[j];
				        gears[j-1] = gears[j];
				        numMat[j-1] = numMat[j];
				        scoAve[j-1] = scoAve[j];
				        gearAve[j-1] = gearAve[j];
				        climbs[j-1] = climbs[j];
				        climbAve[j-1] = climbAve[j];
				        
				        scores[j] = temp;
				        teams[j] = temp2;
				        gears[j] = temp3;
				        numMat[j] = temp4;
				        scoAve[j] = temp5;
				        gearAve[j] = temp6;
				        climbs[j] = temp7;
				        climbAve[j] = temp8;
			        }
	        	} else {
	        		if(by[j-1] < by[j]){  
	        			temp = scores[j-1];
				        temp2 = teams[j-1];
				        temp3 = gears[j-1];
				        temp4 = numMat[j-1];
				        temp5 = scoAve[j-1];
				        temp6 = gearAve[j-1];
				        temp7 = climbs[j-1];
				        temp8 = climbAve[j-1];
				        
				        scores[j-1] = scores[j];
				        teams[j-1] = teams[j];
				        gears[j-1] = gears[j];
				        numMat[j-1] = numMat[j];
				        scoAve[j-1] = scoAve[j];
				        gearAve[j-1] = gearAve[j];
				        climbs[j-1] = climbs[j];
				        climbAve[j-1] = climbAve[j];
				        
				        scores[j] = temp;
				        teams[j] = temp2;
				        gears[j] = temp3;
				        numMat[j] = temp4;
				        scoAve[j] = temp5;
				        gearAve[j] = temp6;
				        climbs[j] = temp7;
				        climbAve[j] = temp8;
			        }
	        	}
	        }  
        }
        String[] string = new String[teams.length*6];
        for(int i = 0; i < n; i++) {
        	if(teams[i] != 0) {
        		String s = teams[i] + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length; i < by.length*2; i++) {
        	if(teams[i - by.length] != 0) {
        		String s = scores[i - (by.length)] + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*2; i < by.length*3; i++) {
        	if(teams[i - by.length*2] != 0) {
        		String s = gears[i - (by.length*2)] + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*3; i < by.length*4; i++) {
        	if(teams[i - by.length*3] != 0) {
        		String s = d.format(scoAve[i - (by.length * 3)]) + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*4; i < by.length*5; i++) {
        	if(teams[i - by.length*4] != 0) {
        		String s = d.format(gearAve[i - (by.length * 4)]) + "\n";
        		string[i] = s;
        	}
        }
        for(int i = by.length*5; i < by.length*6; i++) {
        	if(teams[i - by.length*5] != 0) {
        		String s = d.format(climbAve[i - (by.length * 5)]) + "\n";
        		string[i] = s;
        	}
        }
        return string;
	}
	
	void save() {//Method for saving the information to numerous text files
		try {
			FileWriter teamWriter1 = new FileWriter("teams.txt");
			FileWriter scoreWriter1 = new FileWriter("scores.txt");
			FileWriter gearWriter1 = new FileWriter("gears.txt");
			FileWriter numberMatches1 = new FileWriter("numMat.txt");
			FileWriter scoAve1 = new FileWriter("scoAve.txt");
			FileWriter gearAve1 = new FileWriter("gearAve.txt");
			FileWriter climbWriter1 = new FileWriter("climbs.txt");
			FileWriter climbAveWriter1 = new FileWriter("climbAve.txt");
			
			PrintWriter teamWriter2 = new PrintWriter(teamWriter1);
			PrintWriter scoreWriter2 = new PrintWriter(scoreWriter1);
			PrintWriter gearWriter2 = new PrintWriter(gearWriter1);			
			PrintWriter numberMatches2 = new PrintWriter(numberMatches1);			
			PrintWriter scoAve2 = new PrintWriter(scoAve1);			
			PrintWriter gearAve2 = new PrintWriter(gearAve1);
			PrintWriter climbWriter2 = new PrintWriter(climbWriter1);
			PrintWriter climbAveWriter2 = new PrintWriter(climbAveWriter1);
			
			for(int i = 0; i < teams.length; i++) {
				teamWriter2.println(teams[i]);
				scoreWriter2.println(scores[i]);
				gearWriter2.println(gears[i]);
				numberMatches2.println(numMat[i]);
				scoAve2.println(scoAve[i]);
				gearAve2.println(gearAve[i]);
				climbWriter2.println(climbs[i]);
				climbAveWriter2.println(climbAve[i]);
			}
			
			teamWriter2.close();
			scoreWriter2.close();
			gearWriter2.close();
			numberMatches2.close();
			scoAve2.close();
			gearAve2.close();
			climbWriter2.close();
			climbAveWriter2.close();
			
		} catch(IOException ex) {
		}
	}
	
	void clear() {//Reset all data to 0
		teams = new int[not];
		scores = new int[not];
		gears = new int[not];
		numMat = new int[not];
		scoAve = new double[not];
		gearAve = new double[not];
		climbs = new int[not];
		climbAve = new double[not];
	}
	
	@SuppressWarnings("resource")
	void load() {//Method for loading data from text files
		try {
			FileReader teamReader1 = new FileReader("teams.txt");
			FileReader scoreReader1 = new FileReader("scores.txt");
			FileReader gearReader1 = new FileReader("gears.txt");
			FileReader numberMatches1 = new FileReader("numMat.txt");
			FileReader scoAve1 = new FileReader("scoAve.txt");
			FileReader gearAve1 = new FileReader("gearAve.txt");
			FileReader climbs1 = new FileReader("climbs.txt");
			FileReader climbAve1 = new FileReader("climbAve.txt");
			
			BufferedReader teamReader2 = new BufferedReader(teamReader1);
			BufferedReader scoreReader2 = new BufferedReader(scoreReader1);
			BufferedReader gearReader2 = new BufferedReader(gearReader1);
			BufferedReader numberMatches2 = new BufferedReader(numberMatches1);
			BufferedReader scoAve2 = new BufferedReader(scoAve1);
			BufferedReader gearAve2 = new BufferedReader(gearAve1);
			BufferedReader climbs2 = new BufferedReader(climbs1);
			BufferedReader climbAve2 = new BufferedReader(climbAve1);
			
			for(int i = 0; i < teams.length; i++) {
				teams[i] = Integer.parseInt(teamReader2.readLine());
				scores[i] = Integer.parseInt(scoreReader2.readLine());
				gears[i] = Integer.parseInt(gearReader2.readLine());
				numMat[i] = Integer.parseInt(numberMatches2.readLine());
				scoAve[i] = Double.parseDouble(scoAve2.readLine());
				gearAve[i] = Double.parseDouble(gearAve2.readLine());
				climbs[i] = Integer.parseInt(climbs2.readLine());
				climbAve[i] = Double.parseDouble(climbAve2.readLine());
			}
			
		} catch(IOException ex) {
			out.println("Data file does not exist");
		} catch(java.lang.NumberFormatException ex) {
			@SuppressWarnings("unused")
			Warning warning = new Warning("Invalid Number Format. Check the text file for non-number data");
		}
	}
	
	void fillRandom(int amount) {//Method for filling random information
		if(amount > teams.length) {
			@SuppressWarnings("unused")
			Warning warning = new Warning("The maximum number is " + teams.length);
		} else {
			clear();
			Random random = new Random();
			int matches = random.nextInt(3)+10;
			int times = 1;
			for(int j = 0; j < matches; j++) {
				updateTeam(3603, random.nextInt(200), random.nextInt(7), random.nextInt(5)+1);
			}
			for(int i = 1; i < amount; i++) {
				int randTeam = random.nextInt(highestPossibleTeamNumber) + 1;
				for(int x = 0; x < amount; x++) {
					while(randTeam == teams[x]) {
						System.out.println("Found! " + times);
						randTeam = random.nextInt(highestPossibleTeamNumber) + 1;
						x = 0;
						times++;
					}
				}
				times = 1;
				matches = random.nextInt(3)+10;
				for(int j = 0; j < matches; j++) {
					updateTeam(randTeam, random.nextInt(200), random.nextInt(7), random.nextInt(5)+1);
				}
			}
		}
		doAverages();//Calculate the averages
	}
}