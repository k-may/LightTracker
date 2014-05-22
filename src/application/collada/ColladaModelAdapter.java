package application.collada;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;
import application.base.Triangle;
import application.clients.DataXMLClient;
import application.clients.XMLClient;
import application.interaction.LightData;

public class ColladaModelAdapter {

	private ColladaModelData _model;

	public float[] lightValues;
	private int _length;

	private int _pitch;

	private int _roll;
	private PVector _position;
	private int _yaw;

	DataXMLClient _xml;

	Triangle[] _transformed;
	private boolean _invalidated = true;

	private float _scale;

	/*
	 * contains reference to 3D model
	 */
	public ColladaModelAdapter(ColladaModelData model, DataXMLClient xml) {
		_model = model;
		_xml = xml;

		_pitch = _xml.getPitch();
		_roll = _xml.getRoll();
		_yaw = _xml.getYaw();
		_position = _xml.getPosition();
		_scale = _xml.getScale();

		_length = _model.getOrigTriangles().length;
	}

	public Triangle[] getTriangles() {

		if (_invalidated) {
			_invalidated = false;

			Triangle[] triangles = _model.getOrigTriangles();

			// apply pitch and roll and position?
			PMatrix3D mat = new PMatrix3D();
			mat.scale(_scale);
			mat.translate(_position.x, _position.y, _position.z);
			mat.rotateX(PApplet.radians(_roll));
			mat.rotateY(PApplet.radians(_pitch));
			mat.rotateZ(PApplet.radians(_yaw));

			_transformed = new Triangle[triangles.length];

			for (int i = 0; i < triangles.length; i++) {
				_transformed[i] = triangles[i].applyMatrix(mat);
			}
		}

		return _transformed;
	}

	/*
	 * process incoming light vectors and calculate additive light values on
	 * each face of the model
	 */
	public void handleLightStreamData(ArrayList<LightData> lightData) {

		Triangle[] triangles = getTriangles();
		lightValues = new float[_length];

		for (LightData data : lightData) {
			for (int i = 0; i < _length; i++) {
				PVector lightV = PVector
						.sub(data.position, triangles[i].center);
				float lV = triangles[i].perpNorm.dot(lightV);
				lV = Math.min(0, lV);

				lV *= 255;
				// additive light
				triangles[i].lightValue = (int) lV;
				lightValues[i] += lV;
			}
		}
	}

	public void save() {
		_xml.save(this);
	}

	public int getPitch() {
		return _pitch;
	}

	public void setPitch(int pitch) {
		this._pitch = pitch;
		_invalidated = true;
	}

	public int getRoll() {
		return _roll;
	}

	public void setRoll(int roll) {
		this._roll = roll;
		_invalidated = true;
	}

	public PVector getPosition() {
		return _position;
	}

	public void setPosition(PVector position) {
		this._position = position;
		_invalidated = true;
	}

	public int getYaw() {
		return _yaw;
	}

	public void setYaw(int yaw) {
		this._yaw = yaw;
		_invalidated = true;
	}

	public void setX(int x) {
		_position.x = x;
		_invalidated = true;
	}

	public void setY(int y) {
		_position.y = y;
		_invalidated = true;
	}

	public void setZ(int z) {
		_position.z = z;
		_invalidated = true;
	}

	public float getScale() {
		return _scale;
	}

	public void setScale(float value) {
		_scale = value;
		_invalidated = true;
	}
}
