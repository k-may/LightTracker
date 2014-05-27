package application.view;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import application.interaction.SONRegion;
import application.interaction.Tracker;
import application.interaction.TrackerData;

public class DepthView extends View {

	private Tracker _tracker;

	public DepthView(Tracker tracker) {
		_tracker = tracker;
	}
	
	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub
		super.draw(p);

		int res = 4;
		
		this._y = 450;
		
		p.noStroke();
		p.colorMode(PApplet.RGB);
		
		int[] regions = _tracker.getRegions();
		
		int regionsWidth = Tracker.REGION_WIDTH;
		int regionsHeight = Tracker.REGION_HEIGHT;
		
		for(int x = 0; x < regionsWidth; x ++){
			for(int y = 0; y < regionsHeight; y ++){
				float value = regions[x + y*regionsWidth] ;
				if(value != 0.0){
					p.fill(value, 200,255);
					p.rect(_x + x*res, _y + y*res,res,res);
				}
			}
		}
		
		//draw blobs..
		
		PVector[][] blobs = _tracker.getBlobs();
		p.fill(0);
		for(int b = 0; b < blobs.length; b ++){
			p.beginShape();
			int length = blobs[b].length ;
			for(int t = 0; t < length + 1; t ++){
				int xPos = (int) (_x + blobs[b][t%length].x*res*regionsWidth);
				int yPos = (int) (_y + blobs[b][t%length].y*res*regionsHeight);
				
				p.vertex(xPos,yPos);
			}
			p.endShape();
		}
		
		//draw tracked points
		
		ArrayList<TrackerData> tracked = _tracker.getStream();
		
		p.fill(255,0,0);
		for(TrackerData pt : tracked){
			p.ellipse(_x + pt.position.x*res*regionsWidth,_y + pt.position.y*res*regionsHeight, 10, 10);
		}
		
	}

}
