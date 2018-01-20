package com.main;

import java.util.Random;
import java.awt.Polygon;
import java.awt.Rectangle;

@SuppressWarnings("unused")
public class Scouting_V1 {

	public static void main(String[] args) {
		Window window = new Window(1147, 759);
		window.setLocation(0, 0);
		window.setVisible(true);
		
		Random random = new Random();
		int sides = 30;
		int x[] = new int[sides];
		int y[] = new int[sides];
		/*
		Thread thread = new Thread(() -> {
			
				while(true) {
					for(int i = 0; i < sides; i++) {
						x[i] = random.nextInt(1147);
						y[i] = random.nextInt(759);
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
		thread.start();
		*/
	}
}