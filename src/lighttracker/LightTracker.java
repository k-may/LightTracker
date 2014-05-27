package lighttracker;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;
import SimpleOpenNI.SimpleOpenNI;
import application.AppBuilder;
import application.Controller;
import application.StateManager;
import application.TrackerState;
import application.interaction.SONRegion;
import application.interaction.Tracker;
import application.view.MainView;
import application.view.IView;

@SuppressWarnings("serial")
public class LightTracker extends PApplet implements IView {

	public static PApplet instance;

	public static int CAM_WIDTH = 640;
	public static int CAM_HEIGHT = 420;
	public static int CAM_DEPTH = 2000;

	ArrayList<IView> _childs;
	private MainView _mainView;
	private Tracker _tracker;
	private SONRegion _region;

	public static PVector[] depthData;
	private SimpleOpenNI _son;

	public void setup() {
		size(displayWidth, displayHeight, PApplet.P3D);

		if (_childs == null) {
			_childs = new ArrayList<>();

			background(0);
			noLoop();

			instance = this;
			
			AppBuilder.start(this);
		}
	}

	public void draw() {
		background(255);
		
		_son.update();
		depthData = _son.depthMapRealWorld();
		
		_tracker.digestDepthStream(depthData);
		
		_region.digestTrackerStream(_tracker.getStream());

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
		for (IView child : _childs) {
			child.draw(p);
		}
	}

	public void registerMainView(MainView view) {
		_mainView = view;
	}

	@Override
	public void mousePressed() {
		_mainView.mousePressed(mouseX, mouseY);
	}

	@Override
	public void mouseExited() {
		_mainView.mouseExited(mouseX, mouseY);
	}

	@Override
	public void mouseReleased() {
		_mainView.mouseReleased(mouseX, mouseY);
	}

	@Override
	public void mouseWheel(MouseEvent evt) {
		_mainView.mouseWheel(evt.getCount());
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
		return new PVector(0, 0);
	}

	public void registerSONRegion(SONRegion region) {
		_region = region;
	}

	public void registerTracker(Tracker tracker) {
		_tracker = tracker;
	}

	public Tracker getTracker() {
		return _tracker;
	}

	public void registerSON(SimpleOpenNI son) {
		_son = son;
	}
}
