package application.view;

import java.util.ArrayList;


public class MainView extends View {

	private KinectView _kinectView;
	private ModelView _modelView;
	private ControlsView _controls;
	
	ArrayList<IView> _childs;
	
	public MainView(){
		super();
		init();
		createChilds();
	}
	
	
	private void createChilds() {
		
	}


	private void init() {
		// TODO Auto-generated method stub
		
	}

	public void registerControlsView(ControlsView controls){
		_controls = controls;
		addChild(_controls);
	}

	public void registerModelView(ModelView view){
		_modelView = view;
		addChild(_modelView);
	}
	
	public void registerKinectView(KinectView view){
		_kinectView = view;
		addChild(_kinectView);
	}
	
	/*@Override
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
	public View get_childAt(int index) {
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
*/
}
