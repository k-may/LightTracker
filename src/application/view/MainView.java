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
	
	private int _pitch = 90;
	private int _roll = 180;
	private int _z = -2500;
	
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
		
		p.translate(_width/2 - 200, _height/2, _z);
		p.rotateX(PApplet.radians(180));
		p.rotateY(PApplet.radians(_pitch));
		
		_modelView.draw(p);
		_kinectView.draw(p);
		
		p.popMatrix();
	}


	private void mouseDragged(int mouseX, int mouseY) {
		
		PVector mousePos = new PVector(mouseX, mouseY);
		_pitch += (int) (_downPos.x - mousePos.x)/10;
		_roll += (int) (_downPos.y - mousePos.y)/10 ;
		
		_downPos = mousePos;
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


	public void mouseWheel(int count) {		
		_z -= count*100;
	}

}
