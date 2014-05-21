package application.collada;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PVector;
import application.base.Triangle;
import application.clients.DataXMLClient;
import application.clients.XMLClient;
import application.interaction.LightData;

public class ColladaModelAdapter {

	private ColladaModelData _model;
	public float[] lightValues;
	private int _length;
	
	public int pitch;
	public int roll;
	public PVector position;
	
	DataXMLClient _xml;
	public int yaw;
	/*
	 * contains reference to 3D model
	 */
	public ColladaModelAdapter(ColladaModelData model, DataXMLClient xml){
		_model = model;
		_xml = xml;
		
		pitch = _xml.getPitch();
		roll = _xml.getRoll();
		yaw = _xml.getYaw();
		position = _xml.getPosition();
		
		_length = _model.getTriangles().length;
	}
	
	
	
	public Triangle[] getTriangles(){
		Triangle[] triangles = _model.getTriangles();
		
		//apply pitch and roll and position?
		
		
		return triangles;
	}
	/*
	 * process incoming light vectors and calculate additive light values on
	 * each face of the model
	 */
	public void handleLightStreamData(ArrayList<LightData> lightData) {

		Triangle[] triangles = _model.getTriangles();
		lightValues = new float[_length];
		
		for(LightData data : lightData){
			for(int i = 0 ;i < _length; i ++){
				PVector lightV = PVector.sub(data.position, triangles[i].center);
				float lV = triangles[i].perpNorm.dot(lightV);
				lV = Math.min(0, lV);
				
				lV *= 255;
				//additive light
				triangles[i].lightValue = (int)lV;
				lightValues[i] += lV;
			}
		}
	}


	public void save() {
		_xml.save(this);
	}
}
