package application.interaction;

import processing.core.PVector;

public class TrackerData {
	public int id;
	public PVector position;
	public PVector intension;
	public int lostFrame;
	public float accuracy;
	
	private static int ID;
	
	public TrackerData(PVector pt){
		position = pt;
		lostFrame = 0;
		accuracy = 1.0f;
		intension = new PVector();
		this.id = ID++;
	}
}
