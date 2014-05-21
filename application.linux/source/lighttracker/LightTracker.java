package lighttracker;

import processing.core.PApplet;
import application.AppBuilder;
import application.Controller;
import application.StateManager;
import application.TrackerState;
import application.interaction.SONRegion;

public class LightTracker extends PApplet {

	private SONRegion region;
	private AppBuilder builder;
	public static PApplet instance;

	public static int CAM_WIDTH = 640;
	public static int CAM_HEIGHT = 420;
	public static int CAM_DEPTH = 2000;
	
	
			
	public void setup() {
		instance = this;
		builder = new AppBuilder(this);
		region = builder.getRegion();
	}

	public void draw() {
		Controller.instance.runEvents();
		
		TrackerState state = StateManager.GetState();
		
		switch(state){
			case Alignment:
				
				break;
			case Interaction:
				region.runInteractions();
							
				break;
		}
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { lighttracker.LightTracker.class.getName() });
	}
}
