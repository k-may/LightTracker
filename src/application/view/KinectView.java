package application.view;

import lighttracker.LightTracker;
import application.interaction.SONRegion;
import application.interaction.Tracker;
import processing.core.PApplet;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

public class KinectView extends View {
	

	public KinectView() {
	}
	
	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub

		
		//p.strokeWeight(10);
		p.smooth();
		p.strokeWeight(1);
		p.stroke(0);
		
		PVector[] depthPoints = LightTracker.depthData;
		
		for(int i = 0; i < depthPoints.length; i += 10){
			p.point(depthPoints[i].x, depthPoints[i].y, depthPoints[i].z);
		}
		
		
		super.draw(p);
	}

}
