package com.main;

import java.util.Random;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

@SuppressWarnings("unused")
public class Scouting_V1 {

	public static void main(String[] args) {
		Window window = new Window(1600, 900);
		//window.setLocation(400, 225);
		//window.setUndecorated(true);
		window.setVisible(true);
		Random random = new Random();
		int sides = 102;
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
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//thread.start();
	}
}
