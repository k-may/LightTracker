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

	Knob scale;

	Knob threshold;

	Bang save;

	public ControlsView(ColladaModelAdapter adapter) {
		_adapter = adapter;

		_width = LightTracker.instance.width;
		_height = LightTracker.instance.height;

		createChilds();
	}

	private void createChilds() {
		controller = new ControlP5(LightTracker.instance);

		controller.setColorCaptionLabel(0);
		controller.setAutoSpacing();

		int y = _paddingTop;
		int x = _paddingLeft;

		pitch = controller.addKnob("pitch").setRadius(_radius)
				.setPosition(x, y).setValue(_adapter.getPitch()).setMax(180);
		x += 90;

		roll = controller.addKnob("roll").setRadius(_radius).setPosition(x, y)
				.setMax(180).setValue(_adapter.getRoll());

		x += 90;
		yaw = controller.addKnob("yaw").setRadius(_radius).setPosition(x, y)
				.setMax(180).setValue(_adapter.getYaw());

		x = _paddingLeft;
		y += 140;
		xPos = controller.addKnob("x").setRadius(_radius).setPosition(x, y)
				.setMin(0).setMax(_width).setValue(_adapter.getPosition().x);

		x += 90;

		yPos = controller.addKnob("y").setRadius(_radius).setPosition(x, y)
				.setMin(0).setMax(_height).setValue(_adapter.getPosition().y);

		x += 90;
		zPos = controller.addKnob("z").setRadius(_radius).setPosition(x, y)
				.setMin(-1000).setMax(1000).setValue(_adapter.getPosition().z);

		x = _paddingLeft;
		y += 140;

		scale = controller.addKnob("scale").setRadius(_radius)
				.setPosition(x, y).setMin(-5).setMax(5)
				.setValue(_adapter.getScale());

		threshold = controller.addKnob("threshold").setRadius(_radius)
				.setPosition(_paddingLeft, 700).setMin(0).setMax(2)
				.setValue(_adapter.getBlobThreshold());

		save = controller.addBang("save").setWidth(_radius)
				.setPosition(59, 400);

		controller.addListener(this);

		_invalidated = true;

		_width = 300;

	}

	@Override
	public void draw(PApplet p) {
		scale.setPosition(90, 330);
		save.setPosition(190, 350);

		p.strokeWeight(0.5f);
		p.fill(0, 10);
		p.rect(_x, _y, _width, _height);

		super.draw(p);
	}

	@Override
	public void controlEvent(ControlEvent event) {
		String name = event.getController().getName();
		float value = event.getController().getValue();
		if (name == "pitch") {
			_adapter.setPitch((int) value);
		} else if (name == "roll") {
			_adapter.setRoll((int) value);
		} else if (name == "yaw") {
			_adapter.setYaw((int) value);
		} else if (name == "x") {
			_adapter.setX((int) value);
		} else if (name == "y") {
			_adapter.setY((int) value);
		} else if (name == "z") {
			_adapter.setZ((int) value);
		} else if (name == "save") {
			_adapter.save();
		} else if (name == "scale") {
			_adapter.setScale(value);
		} else if (name == "threshold") {
			_adapter.setBlobThreshold(value);
		}
	}
}
