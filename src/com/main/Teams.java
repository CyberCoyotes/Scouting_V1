package com.main;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Teams {
	
	int highestPossibleTeamNumber = 6999;
	int not = 40; //Number of teams
	int NOATAD = 5; //Number of arrays that are displayed
	
	int teams[] = new int[not]; //Array to store team numbers
	int scores[] = new int[not]; //Array to store scores
	int scales[] = new int[not];
	int switches[] = new int[not];
	double scaleAve[] = new double[not];
	int numMat[] = new int[not]; //Array to store the number of matches played
	double scoAve[] = new double[not]; //Array to store average team scores
	double switchAve[] = new double[not];
	int vaults[] = new int[not];
	double vaultAve[] = new double[not];
	
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
        int temp3 = 0; //Temporary number of matches
        double temp4 = 0; //Temporary average score
        int temp5 = 0;
        double temp6 = 0;
        int temp7 = 0;
        double temp8 = 0;
        //This is a bubble sort algorithm for ordering the stats
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++) {
		        if(by[j-1] < by[j]){  
			        temp = scores[j-1];
			        temp2 = teams[j-1];
			        temp3 = numMat[j-1];
			        temp4 = scoAve[j-1];
			        temp5 = scales[j-1];
			        temp6 = scaleAve[j-1];
			        temp7 = switches[j-1];
			        temp8 = switchAve[j-1];
			        int temp9 = vaults[j-1];
			        double temp10 = vaultAve[j-1];
			        
			        scores[j-1] = scores[j];
			        teams[j-1] = teams[j];
			        numMat[j-1] = numMat[j];
			        scoAve[j-1] = scoAve[j];
			        scales[j-1] = scales[j];
			        scaleAve[j-1] = scaleAve[j];
			        switches[j-1] = switches[j];
			        switchAve[j-1] = switchAve[j];
			        vaults[j-1] = vaults[j];
			        vaultAve[j-1] = vaultAve[j];
			        
			        scores[j] = temp;
			        teams[j] = temp2;
			        numMat[j] = temp3;
			        scoAve[j] = temp4;
			        scales[j] = temp5;
			        scaleAve[j] = temp6;
			        switches[j] = temp7;
			        switchAve[j] = temp8;
			        vaults[j] = temp9;
			        vaultAve[j] = temp10;
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
	        		string[1] = string[1] + h + averagesFormat.format(scoAve[i]) + endH;
	        		string[2] = string[2] + h + averagesFormat.format(scaleAve[i]) + endH;
	        		string[3] = string[3] + h + averagesFormat.format(switchAve[i]) + endH;
	        		string[4] = string[4] + h + averagesFormat.format(vaultAve[i]) + endH;
	        		found = true;
        		} else {
        			string[0] = string[0] + teams[i] + "<br>";
	        		string[1] = string[1] + averagesFormat.format(scoAve[i]) + "<br>";
	        		string[2] = string[2] + averagesFormat.format(scaleAve[i]) + "<br>";
	        		string[3] = string[3] + averagesFormat.format(switchAve[i]) + "<br>";
	        		string[4] = string[4] + averagesFormat.format(vaultAve[i]) + "<br>";
        		}
        	}
        }
        if(!found && teamNum != 0) {
        	new Warning("Team " + teamNum + " is not in the database.");
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
        double temp4 = 0;
        int temp5 = 0;
        double temp6 = 0;
        
        for(int i=0; i < n; i++){  
	        for(int j=1; j < (n-i); j++) {
	        	if(by == teams) {
			        if(by[j-1] > by[j]){  
			        	temp = scores[j-1];
				        temp2 = teams[j-1];
				        temp3 = numMat[j-1];
				        temp4 = scoAve[j-1];
				        temp5 = scales[j-1];
				        temp6 = scaleAve[j-1];
				        int temp7 = switches[j-1];
				        double temp8 = switchAve[j-1];
				        int temp9 = vaults[j-1];
				        double temp10 = vaultAve[j-1];
				        
				        scores[j-1] = scores[j];
				        teams[j-1] = teams[j];
				        numMat[j-1] = numMat[j];
				        scoAve[j-1] = scoAve[j];
				        scales[j-1] = scales[j];
				        scaleAve[j-1] = scaleAve[j];
				        switches[j-1] = switches[j];
				        switchAve[j-1] = switchAve[j];
				        vaults[j-1] = vaults[j];
				        vaultAve[j-1] = vaultAve[j];
				        
				        scores[j] = temp;
				        teams[j] = temp2;
				        numMat[j] = temp3;
				        scoAve[j] = temp4;
				        scales[j] = temp5;
				        scaleAve[j] = temp6;
				        switches[j] = temp7;
				        switchAve[j] = temp8;
				        vaults[j] = temp9;
				        vaultAve[j] = temp10;
			        }
	        	} else {
	        		if(by[j-1] < by[j]){  
	        			temp = scores[j-1];
				        temp2 = teams[j-1];
				        temp3 = numMat[j-1];
				        temp4 = scoAve[j-1];
				        temp5 = scales[j-1];
				        temp6 = scaleAve[j-1];
				        int temp7 = switches[j-1];
				        double temp8 = switchAve[j-1];
				        int temp9 = vaults[j-1];
				        double temp10 = vaultAve[j-1];
				        
				        scores[j-1] = scores[j];
				        teams[j-1] = teams[j];
				        numMat[j-1] = numMat[j];
				        scoAve[j-1] = scoAve[j];
				        scales[j-1] = scales[j];
				        scaleAve[j-1] = scaleAve[j];
				        switches[j-1] = switches[j];
				        switchAve[j-1] = switchAve[j];
				        vaults[j-1] = vaults[j];
				        vaultAve[j-1] = vaultAve[j];
				        
				        scores[j] = temp;
				        teams[j] = temp2;
				        numMat[j] = temp3;
				        scoAve[j] = temp4;
				        scales[j] = temp5;
				        scaleAve[j] = temp6;
				        switches[j] = temp7;
				        switchAve[j] = temp8;
				        vaults[j] = temp9;
				        vaultAve[j] = temp10;
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
			FileWriter numberMatches1 = new FileWriter("numMat.txt");
			FileWriter scales1 = new FileWriter("scales.txt");
			FileWriter switches1 = new FileWriter("switches.txt");
			FileWriter vaults1 = new FileWriter("vaults.txt");
			
			PrintWriter teamWriter2 = new PrintWriter(teamWriter1);
			PrintWriter scoreWriter2 = new PrintWriter(scoreWriter1);	
			PrintWriter numberMatches2 = new PrintWriter(numberMatches1);
			PrintWriter scales2 = new PrintWriter(scales1);
			PrintWriter switches2 = new PrintWriter(switches1);
			PrintWriter vaults2 = new PrintWriter(vaults1);
			
			for(int i = 0; i < teams.length; i++) {
				teamWriter2.println(teams[i]);
				scoreWriter2.println(scores[i]);
				numberMatches2.println(numMat[i]);
				scales2.println(scales[i]);
				switches2.println(switches[i]);
				vaults2.println(vaults[i]);
			}
			
			teamWriter2.close();
			scoreWriter2.close();
			numberMatches2.close();
			scales2.close();
			switches2.close();
			vaults2.close();
			
		} catch(IOException ex) {
		}
	}
	
	void clear() {//Reset all data to 0
		teams = new int[not];
		scores = new int[not];
		numMat = new int[not];
		scoAve = new double[not];
		scales = new int[not];
		scaleAve = new double[not];
		switches = new int[not];
		switchAve = new double[not];
		vaults = new int[not];
		vaultAve = new double[not];
	}
	
	@SuppressWarnings("resource")
	void load() {//Method for loading data from text files
		try {
			FileReader teamReader1 = new FileReader("teams.txt");
			FileReader scoreReader1 = new FileReader("scores.txt");
			FileReader numberMatches1 = new FileReader("numMat.txt");
			FileReader scales1 = new FileReader("scales.txt");
			FileReader switches1 = new FileReader("switches.txt");
			FileReader vaults1 = new FileReader("vaults.txt");
			
			BufferedReader teamReader2 = new BufferedReader(teamReader1);
			BufferedReader scoreReader2 = new BufferedReader(scoreReader1);
			BufferedReader numberMatches2 = new BufferedReader(numberMatches1);
			BufferedReader scales2 = new BufferedReader(scales1);
			BufferedReader switches2 = new BufferedReader(switches1);
			BufferedReader vaults2 = new BufferedReader(vaults1);
			
			for(int i = 0; i < teams.length; i++) {
				teams[i] = Integer.parseInt(teamReader2.readLine());
				scores[i] = Integer.parseInt(scoreReader2.readLine());
				numMat[i] = Integer.parseInt(numberMatches2.readLine());
				scales[i] = Integer.parseInt(scales2.readLine());
				switches[i] = Integer.parseInt(switches2.readLine());
				vaults[i] = Integer.parseInt(vaults2.readLine());
			}
			
			doAverages();
		} catch(IOException ex) {
			out.println("Data file does not exist");
		} catch(java.lang.NumberFormatException ex) {
			new Warning("Invalid Number Format. Check the text file for non-number data");
		}
	}
	
	void updateTeam(int teamNumber, int score, int scale, int swi, int vault) { //Method to update team info after a match
		
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
			scales[value] = scales[value] + scale;
			switches[value] = switches[value] + swi;
			vaults[value] = vaults[value] + vault;
			numMat[value]++; //Increase the number of matches they've played in
		} else { //If the team doesn't already exist...
			addTeam(teamNumber, score, scale, swi, vault); //Add a new team with the requested information
		}
		doAverages(); //Calculate the new averages
	}
	
	void addTeam(int teamNumber, int score, int scale, int swi, int vault) { //Method for adding a team
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
				scales[value] = scale;
				switches[value] = swi;
				vaults[value] = vault;
				numMat[value] = 1;//Set their number of matches to 1
			} else {//If there isn't an empty slot...
				new Warning("Too many teams. The maximum number of teams is " + teams.length);
			}
			
		} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}
	
	void fillRandom(int amount) {//Method for filling random information
		if(amount > teams.length) {
			new Warning("The maximum number is " + teams.length);
		} else {
			
		}
		doAverages();//Calculate the averages
	}
	
	void doAverages() { //Method to calculate averages
		for(int i = 0; i < teams.length; i++) {
			if(numMat[i] != 0) { //To keep from dividing by zero
				scoAve[i] = (double) scores[i] / numMat[i]; //Calculate score averages
				scaleAve[i] = (double) scales[i] / numMat[i];
				switchAve[i] = (double) switches[i] / numMat[i];
				vaultAve[i] = (double) vaults[i] / numMat[i];
			}
		}
	}
}