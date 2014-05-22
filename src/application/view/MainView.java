package application.view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class MainView extends View {

	private KinectView _kinectView;
	private ModelView _modelView;
	private ControlsView _controls;
	
	ArrayList<IView> _childs;
	
	private int _pitch = 0;
	private int _roll = 0;
	
	private int _downTime;
	private PVector _downPos;
	private boolean _mouseDown = false;
	
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
	
	@Override
	public void draw(PApplet p) {
		if (!_isVisible)
			return;
		
		if(_mouseDown)
			mouseDragged(p.mouseX, p.mouseY);
		
		_controls.draw(p);
		
		p.pushMatrix();
		
		p.translate(_width/2, _height/2);
		p.rotateX(PApplet.radians(_roll));
		p.rotateY(PApplet.radians(_pitch));
		
		_modelView.draw(p);
		_kinectView.draw(p);
		
		p.popMatrix();
	}


	private void mouseDragged(int mouseX, int mouseY) {
		
		Point mousePos = new Point(mouseX, mouseY);
		
		//double distance = Point.distance(_downPos.x, _downPos.y, mousePos.x, mousePos.y);
		
		_pitch = (int) ((_downPos.x - mousePos.x) / _width);
		_roll = (int) ((_downPos.y - mousePos.y) / _height);
		
	}


	public void mouseExited(int x, int y) {
		_mouseDown = false;
	}


	public void mousePressed(int x, int y) {

		if(!_mouseDown)
			_downPos = new PVector(x, y);
		
		_mouseDown = true;
	}


	public void mouseReleased(int x, int y) {
		_mouseDown = false;
	}

}
