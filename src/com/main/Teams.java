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
	int NOATAD = 8; //Number of arrays that are displayed
	
	int teams[] = new int[not]; //Array to store team numbers
	int scores[] = new int[not]; //Array to store scores
	int numMat[] = new int[not]; //Array to store the number of matches played
	double scoAve[] = new double[not]; //Array to store average team scores
	double gearAve[] = new double[not]; //Array to store average gears
	int climbs[] = new int[not]; //Array to store total number of climb points
	double climbAve[] = new double[not]; //Array to store average number of climb points
	int gears[] = new int[not]; //Array to store total number of gears
	int fuels[] = new int[not];
	double fuelAve[] = new double[not];
	double composite[] = new double[not];
	
	double scoreWeight;
	double gearWeight;
	double climbWeight;
	double fuelWeight;
	
	boolean embolden = false;
	int teamNum = 0;
	
	String h = "<b><span style=\"background-color: #FFFF00\">";
	String endH = "</span></b><br>";
	
	DecimalFormat averagesFormat = new DecimalFormat("#.00");
	DecimalFormat compFormat = new DecimalFormat("#0.000");
	
	public Teams() {
		load(); //Reload any saved data
	}
	
	void bold(boolean b, int teamNumber) {
		embolden = b;
		if(embolden) {
			teamNum = teamNumber;
		} else {
			teamNum = 0;
		}
	}
	
	String[] sort(double[] by) {//Method to sort by an average. "by" is short for sort by
		int n = by.length;  //Save the length of the requested array
        int temp = 0; //Temporary score
        int temp2 = 0; //Temporary team
        int temp3 = 0; //Temporary gear
        int temp4 = 0; //Temporary number of matches
        double temp5 = 0; //Temporary average score
        double temp6 = 0; //Temporary average gear
        int temp7 = 0; //Temporary total climb rank
        double temp8 = 0; //Temporary average climb rank
        int temp9 = 0;
        double temp10 = 0;
        double temp11 = 0;
        
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
			        temp9 = fuels[j-1];
			        temp10 = fuelAve[j-1];
			        temp11 = composite[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        gears[j-1] = gears[j];
			        numMat[j-1] = numMat[j];
			        scoAve[j-1] = scoAve[j];
			        gearAve[j-1] = gearAve[j];
			        climbs[j-1] = climbs[j];
			        climbAve[j-1] = climbAve[j];
			        fuels[j-1] = fuels[j];
			        fuelAve[j-1] = fuelAve[j];
			        composite[j-1] = composite[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        gears[j] = temp3;
			        numMat[j] = temp4;
			        scoAve[j] = temp5;
			        gearAve[j] = temp6;
			        climbs[j] = temp7;
			        climbAve[j] = temp8;
			        fuels[j] = temp9;
			        fuelAve[j] = temp10;
			        composite[j] = temp11;
		        }
	        }  
        }
        return formatedInfo();
	}
	
	String[] formatedInfo() {
		boolean found = false;
		String[] string = new String[NOATAD];
        for(int i = 0; i < string.length; i++) {
        	string[i] = "<font face=\"Arial\">";
        }
        for(int i = 0; i < teams.length; i++) {
        	if(teams[i] != 0) {
        		if(embolden && teams[i] == teamNum) {
    				string[0] = string[0] + h + teams[i] + endH;
	        		string[1] = string[1] + h + scores[i] + endH;
	        		string[2] = string[2] + h + gears[i] + endH;
	        		string[3] = string[3] + h + averagesFormat.format(scoAve[i]) + endH;
	        		string[4] = string[4] + h + averagesFormat.format(gearAve[i]) + endH;
	        		string[5] = string[5] + h + averagesFormat.format(climbAve[i]) + endH;
	        		string[6] = string[6] + h + averagesFormat.format(fuelAve[i]) + endH;
	        		string[7] = string[7] + h + compFormat.format(composite[i]) + endH;
	        		found = true;
        		} else {
        			string[0] = string[0] + teams[i] + "<br>";
	        		string[1] = string[1] + scores[i] + "<br>";
	        		string[2] = string[2] + gears[i] + "<br>";
	        		string[3] = string[3] + averagesFormat.format(scoAve[i]) + "<br>";
	        		string[4] = string[4] + averagesFormat.format(gearAve[i]) + "<br>";
	        		string[5] = string[5] + averagesFormat.format(climbAve[i]) + "<br>";
	        		string[6] = string[6] + averagesFormat.format(fuelAve[i]) + "<br>";
	        		string[7] = string[7] + compFormat.format(composite[i]) + "<br>";
        		}
        	}
        }
        if(!found && teamNum != 0) {
        	@SuppressWarnings("unused")
			Warning warning = new Warning("Team " + teamNum + " is not in the database.");
        }
        for(int i = 0; i < string.length; i++) {
        	string[i] = string[i] + "</font face=\"Arial\"";
        }
        return string;
	}
	
	String[] sort(int[] by) {//Same as the other score but uses the integer type
		int n = by.length;  
        int temp = 0;
        int temp2 = 0;
        int temp3 = 0;
        int temp4 = 0;
        double temp5 = 0;
        double temp6 = 0;
        int temp7 = 0;
        double temp8 = 0;
        int temp9 = 0;
        double temp10 = 0;
        double temp11 = 0;
        
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
				        temp9 = fuels[j-1];
				        temp10 = fuelAve[j-1];
				        temp11 = composite[j-1];
				        
				        scores[j-1] = scores[j];
				        teams[j-1] = teams[j];
				        gears[j-1] = gears[j];
				        numMat[j-1] = numMat[j];
				        scoAve[j-1] = scoAve[j];
				        gearAve[j-1] = gearAve[j];
				        climbs[j-1] = climbs[j];
				        climbAve[j-1] = climbAve[j];
				        fuels[j-1] = fuels[j];
				        fuelAve[j-1] = fuelAve[j];
				        composite[j-1] = composite[j];
				        
				        scores[j] = temp;
				        teams[j] = temp2;
				        gears[j] = temp3;
				        numMat[j] = temp4;
				        scoAve[j] = temp5;
				        gearAve[j] = temp6;
				        climbs[j] = temp7;
				        climbAve[j] = temp8;
				        fuels[j] = temp9;
				        fuelAve[j] = temp10;
				        composite[j] = temp11;
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
				        temp9 = fuels[j-1];
				        temp10 = fuelAve[j-1];
				        temp11 = composite[j-1];
				        
				        scores[j-1] = scores[j];
				        teams[j-1] = teams[j];
				        gears[j-1] = gears[j];
				        numMat[j-1] = numMat[j];
				        scoAve[j-1] = scoAve[j];
				        gearAve[j-1] = gearAve[j];
				        climbs[j-1] = climbs[j];
				        climbAve[j-1] = climbAve[j];
				        fuels[j-1] = fuels[j];
				        fuelAve[j-1] = fuelAve[j];
				        composite[j-1] = composite[j];
				        
				        scores[j] = temp;
				        teams[j] = temp2;
				        gears[j] = temp3;
				        numMat[j] = temp4;
				        scoAve[j] = temp5;
				        gearAve[j] = temp6;
				        climbs[j] = temp7;
				        climbAve[j] = temp8;
				        fuels[j] = temp9;
				        fuelAve[j] = temp10;
				        composite[j] = temp11;
			        }
	        	}
	        }  
        }
        
        return formatedInfo();
	}
	
	void save() {//Method for saving the information to numerous text files
		try {
			FileWriter teamWriter1 = new FileWriter("teams.txt");
			FileWriter scoreWriter1 = new FileWriter("scores.txt");
			FileWriter gearWriter1 = new FileWriter("gears.txt");
			FileWriter numberMatches1 = new FileWriter("numMat.txt");
			FileWriter climbWriter1 = new FileWriter("climbs.txt");
			FileWriter fuelWriter1 = new FileWriter("fuel.txt");
			FileWriter comps1 = new FileWriter("comps1.txt");
			
			PrintWriter teamWriter2 = new PrintWriter(teamWriter1);
			PrintWriter scoreWriter2 = new PrintWriter(scoreWriter1);
			PrintWriter gearWriter2 = new PrintWriter(gearWriter1);			
			PrintWriter numberMatches2 = new PrintWriter(numberMatches1);
			PrintWriter climbWriter2 = new PrintWriter(climbWriter1);
			PrintWriter fuelWriter2 = new PrintWriter(fuelWriter1);
			PrintWriter comps2 = new PrintWriter(comps1);
			
			for(int i = 0; i < teams.length; i++) {
				teamWriter2.println(teams[i]);
				scoreWriter2.println(scores[i]);
				gearWriter2.println(gears[i]);
				numberMatches2.println(numMat[i]);
				climbWriter2.println(climbs[i]);
				fuelWriter2.println(fuels[i]);
			}
			comps2.println(scoreWeight);
			comps2.println(gearWeight);
			comps2.println(climbWeight);
			comps2.println(fuelWeight);
			
			teamWriter2.close();
			scoreWriter2.close();
			gearWriter2.close();
			numberMatches2.close();
			climbWriter2.close();
			fuelWriter2.close();
			comps2.close();
			
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
		fuels = new int[not];
		fuelAve = new double[not];
		composite = new double[not];
	}
	
	@SuppressWarnings("resource")
	void load() {//Method for loading data from text files
		try {
			FileReader teamReader1 = new FileReader("teams.txt");
			FileReader scoreReader1 = new FileReader("scores.txt");
			FileReader gearReader1 = new FileReader("gears.txt");
			FileReader numberMatches1 = new FileReader("numMat.txt");
			FileReader climbs1 = new FileReader("climbs.txt");
			FileReader fuel1 = new FileReader("fuel.txt");
			FileReader comps1 = new FileReader("comps1.txt");
			
			BufferedReader teamReader2 = new BufferedReader(teamReader1);
			BufferedReader scoreReader2 = new BufferedReader(scoreReader1);
			BufferedReader gearReader2 = new BufferedReader(gearReader1);
			BufferedReader numberMatches2 = new BufferedReader(numberMatches1);
			BufferedReader climbs2 = new BufferedReader(climbs1);
			BufferedReader fuel2 = new BufferedReader(fuel1);
			BufferedReader comps2 = new BufferedReader(comps1);
			
			for(int i = 0; i < teams.length; i++) {
				teams[i] = Integer.parseInt(teamReader2.readLine());
				scores[i] = Integer.parseInt(scoreReader2.readLine());
				gears[i] = Integer.parseInt(gearReader2.readLine());
				numMat[i] = Integer.parseInt(numberMatches2.readLine());
				climbs[i] = Integer.parseInt(climbs2.readLine());
				fuels[i] = Integer.parseInt(fuel2.readLine());
			}
			scoreWeight = Double.parseDouble(comps2.readLine());
			gearWeight = Double.parseDouble(comps2.readLine());
			climbWeight = Double.parseDouble(comps2.readLine());
			fuelWeight = Double.parseDouble(comps2.readLine());
			
			doAverages();
		} catch(IOException ex) {
			out.println("Data file does not exist");
		} catch(java.lang.NumberFormatException ex) {
			@SuppressWarnings("unused")
			Warning warning = new Warning("Invalid Number Format. Check the text file for non-number data");
		}
	}
	
	void updateTeam(int teamNumber, int score, int gear, int climb, int fuel) { //Method to update team info after a match
		
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
			fuels[value] = fuels[value] + fuel;
			numMat[value]++; //Increase the number of matches they've played in
		} else { //If the team doesn't already exist...
			addTeam(teamNumber, score, gear, climb, fuel); //Add a new team with the requested information
		}
		doAverages(); //Calculate the new averages
	}
	
	void addTeam(int teamNumber, int score, int gear, int climb, int fuel) { //Method for adding a team
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
				fuels[value] = fuel;
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
	
	void fillRandom(int amount) {//Method for filling random information
		if(amount > teams.length) {
			@SuppressWarnings("unused")
			Warning warning = new Warning("The maximum number is " + teams.length);
		} else {
			clear();
			Random random = new Random();
			{
			int o = random.nextInt(3);
			int g = random.nextInt(3 + o);
			int f = random.nextInt(14 + o);
			int c = random.nextInt(1 + o);
			int matches = random.nextInt(3)+10;
			for(int j = 0; j < matches; j++) {
				int gear = random.nextInt(5) + g;
				int gearScore = gear;
				int fuel = random.nextInt(16) + f;
				int fuelScore = fuel;
				int climb = random.nextInt(4) + c;
				int climbScore = climb;
				int score = 0;
				if(fuel % 3 > 0) {
					fuelScore = fuel - (fuel % 3);
				}
				score = fuelScore/3;
				
				if(gear >= 3) {
					gearScore = gear + 40;
				}
				score = score +gearScore;
				if(climb >= 3) {
					climbScore = climb + 40;
				}
				score = score + climbScore;
				updateTeam(3603, score, gear, climb, fuel);
			}
			}
			
			for(int i = 1; i < amount; i++) {
				int times = 1;
				int randTeam = random.nextInt(highestPossibleTeamNumber) + 1;
				int matches = random.nextInt(3)+10;
				for(int x = 0; x < amount; x++) {
					while(randTeam == teams[x]) {
						System.out.println("Found! " + times);
						randTeam = random.nextInt(highestPossibleTeamNumber) + 1;
						x = 0;
						times++;
					}
				}
				int o = random.nextInt(3);
				int g = random.nextInt(3 + o);
				int f = random.nextInt(14 + o);
				int c = random.nextInt(2 + o);
				for(int j = 0; j < matches; j++) {
					int gear = random.nextInt(5) + g;
					int gearScore = gear*20;
					int fuel = random.nextInt(16) + f;
					int fuelScore = fuel;
					int climb = random.nextInt(4) + c;
					int climbScore = climb;
					int score = 0;
					if(fuel % 3 > 0) {
						fuelScore = fuel - (fuel % 3);
					}
					score = fuelScore/3;
					
					if(gear >= 3) {
						gearScore = gearScore + 40;
					}
					score = score + gearScore;
					if(climb >= 3) {
						climbScore = climb + 40;
					}
					score = score + climbScore;
					updateTeam(randTeam, score, gear, climb, fuel);
				}
			}
		}
		doAverages();//Calculate the averages
	}
	
	void doAverages() { //Method to calculate averages
		for(int i = 0; i < teams.length; i++) {
			if(numMat[i] != 0) { //To keep from dividing by zero
				scoAve[i] = (double) scores[i] / numMat[i]; //Calculate score averages
				gearAve[i] = (double) gears[i] / numMat[i]; //Calculate gear averages
				climbAve[i] = (double) climbs[i] / numMat[i]; //Calculate climb averages
				fuelAve[i] = (double) fuels[i] / numMat[i];
				composite[i] = (double) fuelWeight * fuelAve[i] + climbWeight * climbAve[i] + gearWeight * gearAve[i] + scoreWeight * scoAve[i];
				compFormat.format(composite[i]);
			}
		}
	}
}