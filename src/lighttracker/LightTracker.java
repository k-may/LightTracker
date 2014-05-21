package lighttracker;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import application.AppBuilder;
import application.Controller;
import application.StateManager;
import application.TrackerState;
import application.interaction.SONRegion;
import application.view.MainView;
import application.view.IView;

@SuppressWarnings("serial")
public class LightTracker extends PApplet implements IView {

	private SONRegion region;
	private AppBuilder builder;
	private MainView _view;
	public static PApplet instance;

	public static int CAM_WIDTH = 640;
	public static int CAM_HEIGHT = 420;
	public static int CAM_DEPTH = 2000;
	
	ArrayList<IView> _childs;
	
	
			
	public void setup() {
		size(displayWidth, displayHeight, PApplet.P3D);
		
		if (_childs == null) {
			_childs = new ArrayList<>();

			background(0);
			noLoop();

			instance = this;
			builder = new AppBuilder(this);
			region = builder.getRegion();
		}


	}

	public void draw() {
		background(255);
		
		Controller.instance.runEvents();
		
		TrackerState state = StateManager.GetState();
		
		switch(state){
			case Alignment:
				
				break;
			case Interaction:
				region.runInteractions();
							
				break;
		}
		
		draw(this);
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { lighttracker.LightTracker.class.getName() });
	}
	
	@Override
	public boolean sketchFullScreen() {
		return true;
	}

	@Override
	public void draw(PApplet p) {
		for(IView child : _childs){
			child.draw(p);
		}
	}

	@Override
	public void addChild(IView child) {
		_childs.add(child);
		child.set_parent(this);
	}

	@Override
	public void removeChild(IView child) {
		_childs.remove(child);
		child.set_parent(null);
	}

	@Override
	public void set_parent(IView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IView get_parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVisible(Boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_numChildren() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IView get_childAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float get_x() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float get_y() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float get_width() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public float get_height() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int get_id() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PVector get_absPos() {
		// TODO Auto-generated method stub
		return new PVector(0,0);
	}
}
