package application.view;

import application.collada.ColladaModelAdapter;
import processing.core.PApplet;
import processing.core.PVector;
import lighttracker.LightTracker;
import controlP5.Bang;
import controlP5.CallbackListener;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.Knob;
import controlP5.Numberbox;

public class ControlsView extends View implements ControlListener {

	ControlP5 controller;
	Knob _pitch;
	Knob _roll;
	Numberbox _xPos;
	Numberbox _yPos;
	Numberbox _zPos;

	ColladaModelAdapter _adapter;

	private int _paddingLeft = 50;
	private int _paddingTop = 50;
	private int _radius = 20;

	Knob pitch;
	Knob roll;
	Knob yaw;
	
	Knob xPos;
	Knob yPos;
	Knob zPos;
	
	Bang save;

	public ControlsView(ColladaModelAdapter adapter) {
		_adapter = adapter;

		_width = LightTracker.instance.width;
		_height = LightTracker.instance.height;
		
		createChilds();
	}

	private void createChilds() {
		controller = new ControlP5(LightTracker.instance);

		controller.setAutoSpacing();
		
		int y = _paddingTop;
		int x = _paddingLeft;

		int knobInc = _radius * 2 + 20;

		pitch = controller.addKnob("pitch").setRadius(_radius)
				.setPosition(x, y).setValue(_adapter.pitch).setMax(180);
		y += knobInc;

		roll = controller.addKnob("roll").setRadius(_radius).setPosition(x, y)
				.setMax(180).setValue(_adapter.roll);
		
		y += knobInc;
		yaw = controller.addKnob("yaw").setRadius(_radius).setPosition(x, y)
				.setMax(180).setValue(_adapter.yaw);
		
		y += knobInc;
		xPos = controller.addKnob("x").setRadius(_radius).setPosition(x, y)
				.setMin(0).setMax(_width).setValue(_adapter.position.x);
		
		y += knobInc;
		
		yPos = controller.addKnob("y").setRadius(_radius).setPosition(x, y)
				.setMin(0).setMax(_height).setValue(_adapter.position.y);
		
		y += knobInc;
		
		zPos = controller.addKnob("z").setRadius(_radius).setPosition(x, y)
				.setMin(-1000).setMax(1000).setValue(_adapter.position.z);
		
		y += knobInc;

		save = controller.addBang("save").setWidth(_radius).setPosition(x, y);

		y += 30;

		controller.addListener(this);

		_invalidated = true;

	}

	@Override
	public void draw(PApplet p) {
		if (_invalidated) {
			_invalidated = false;
			// controller.getDefaultTab().setAbsolutePosition(getA)

		}
		super.draw(p);
	}

	@Override
	public void controlEvent(ControlEvent event) {
		String name = event.getController().getName();
		
		if (name == "pitch") {
			_adapter.pitch = (int) event.getController().getValue();
		} else if (name == "roll") {
			_adapter.roll = (int) event.getController().getValue();
		} else if (name == "yaw") {
			_adapter.yaw = (int) event.getController().getValue();
		}else if(name == "x"){
			_adapter.position.x = event.getController().getValue();
		}else if(name == "y"){
			_adapter.position.y = event.getController().getValue();
		}else if(name == "z"){
			_adapter.position.z = event.getController().getValue();
		} else if (name == "save") {
			_adapter.save();
		}
	}

	private void createKnob(String name, float x, float y) {
		controller.addKnob(name).setRadius(_radius).setPosition(x, y);
	}

}
