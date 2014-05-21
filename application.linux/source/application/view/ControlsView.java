package application.view;

import processing.core.PApplet;
import processing.core.PVector;
import lighttracker.LightTracker;
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

	private int _paddingLeft = 50;

	public ControlsView() {
		createChilds();
	}

	private void createChilds() {
		controller = new ControlP5(LightTracker.instance);
		controller.addListener(this);
		createKnob("pitch", _paddingLeft, 0);
		createKnob("roll", _paddingLeft, 30);
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

		} else if (name == "roll") {

		}
	}

	private void createKnob(String name, float x, float y) {
		controller.addKnob(name).setRadius(50).setPosition(x, y);
	}

}
