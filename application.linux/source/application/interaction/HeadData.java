package application.interaction;

import java.util.ArrayList;

import processing.core.PVector;

public class HeadData {

	private int _id;

	private PVector _position;

	private ArrayList<PVector> _positions;
	private final float DAMPENING = 0.5f;

	public HeadData() {
		_positions = new ArrayList<PVector>();
	}

	public void addPosition(PVector pos) {
		if (_position != null)
			_position = PVector.lerp(_position, pos, DAMPENING);
		else
			_position = pos;

		_positions.add(_position);
	}

	public PVector getPosition(){
		return _position;
	}
	
	public int get_id() {
		return _id;
	}
}
