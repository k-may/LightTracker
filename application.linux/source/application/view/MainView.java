package application.view;

import processing.core.PApplet;
import processing.core.PVector;

public class MainView implements IView {

	private KinectView _kinectView;
	private ModelView _modelView;
	private ControlsView _controls;
	
	public MainView(){
		init();
		createChilds();
	}
	
	
	private void createChilds() {
		_controls = new ControlsView();
	}


	private void init() {
		// TODO Auto-generated method stub
		
	}


	public void registerModelView(ModelView view){
		_modelView = view;
	}
	
	public void registerKinectView(KinectView view){
		_kinectView = view;
	}
	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChild(IView child) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeChild(IView child) {
		// TODO Auto-generated method stub

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
		return 0;
	}

	@Override
	public float get_height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_id() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public PVector get_absPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
