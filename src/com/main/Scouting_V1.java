package com.main;

import java.util.Random;
import java.awt.Polygon;
import java.awt.Rectangle;

@SuppressWarnings("unused")
public class Scouting_V1 {

	public static void main(String[] args) {
		Window window = new Window(897, 759);
		window.setLocation(0, 8);
		//window.setUndecorated(true);
		window.setVisible(true);
		/*
		Random random = new Random();
		int sides = 30;
		int x[] = new int[sides];
		int y[] = new int[sides];
		Thread thread = new Thread(() -> {
			while(true) {
				for(int i = 0; i < sides; i++) {
					x[i] = random.nextInt(1600);
					y[i] = random.nextInt(900);
				}
				Polygon polygon = new Polygon(x, y, sides);
				window.setShape(polygon);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		//thread.start();
		*/
	}
}

/**
 * 1. Create a new button
 * 2. Set bounds
 * 3. Add the coloring code and add a new case to the switch
 * 4. Create a new text field for input
 * 5. Set bounds
 * 6. Add "getText" to the update team field in Window
 * 7. Add a new text area for display
 * 8. Set bounds and color
 * 9. Go to teams
 * 10. If it's an average...
 *		A. Create an integer array type to store totals
 *		B. Create a double array type to store averages
 *		C. In "updateTeam", add a new integer parameter for the new data
 *		D. Next, if the team is found, take the previous data in the totals array and add it to the new parameter
 *		E. If the team is not found...
 *			I. Add a new integer parameter for the new data in "addTeam"
 *			II. If there is an empty slot, set that point in the totals array equal to the new data
 *		F. Create an integer temp for the totals and a double temp for the averages in "sort(double[] by)"
 *		G. "tempX = totals[j-1]"
 *		H. "totals[j-1] = totals[j]"
 *		I. "totals[j] = tempX"
 *		J. Repeat G-I for the averages array
 *		K. Go to the last for loop in "sort(double[] by)"
 *		L. Create a new for loop containing:
 *			I. for(int i = n*x; i < n*(x+1); i++) {
 *			II.    if(teams[i - n*x] != 0) {
 *			III.       String s = d.format(AVERAGE_ARRAY[i - (n * x)]) + "\n";
 *			IV.        string[i] = s;
 *			V.     }
 *			VI. }
 *			VII.x++;
 *		M. Replace "AVERAGE_ARRAY" with the new average array.
 *		N. Repeat steps F through M in "sort(int[] by)" Make sure to put steps G through I in both parts of the if statement
 *		O. In "clear", make the totals array equal a new int. Do the same for the averages array
 *		P. In "load", create a file reader for the totals array.
 *		Q. Add a buffered reader for the totals array
 *		R. Add "TOTALS[i] = Integer.parseInt(TOTALS_READER.readLine());"
 *		S. Repeat steps P through R for the averages array
 *		T. In "save", create a file writer for the totals array
 *		U. Add a buffered writer for the totals array
 *		V. Add "TOTALS_WRITER.println(TOTALS[i]);
 *		W. Repeat steps T through V for the averages array
 *		X. In "doAverages", add the math to calculate the averages for the new data.
 *		Y. In "fillRandom", in both "updateTeam" commands, add a random for the data
 *11. If it's a total, do everything from step 10, but skip the parts for averages
 *12. In "actionPerformed", add a part that says if the new button is clicked, set the sort selection to x.
 *13. Add another for loop in "displayData" for the new text area
 *14. Add an action listener to the button
 *15. Disable the text area
 *16. After the "updateTeam" function is called, clear the text input field
 *17. Done!
 */
