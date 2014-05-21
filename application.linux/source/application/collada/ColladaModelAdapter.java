package application.collada;

import java.util.ArrayList;

import cooladaLoader.Triangle;

import application.interaction.LightData;

public class ColladaModelAdapter {

	private ColladaModelData _model;
	/*
	 * contains reference to 3D model
	 */
	public ColladaModelAdapter(ColladaModelData data){
		_model = data;
	}
	
	/*
	 * process incoming light vectors and calculate additive light values on
	 * each face of the model
	 */
	public void handleLightStreamData(ArrayList<LightData> lightData) {

		Triangle[] triangles = _model.getTriangles();
	}
}
