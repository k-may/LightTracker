package application.collada;

import processing.core.PApplet;

public class ModelFacade {

	public ModelFacade(PApplet parent){
		ColladaModelData data = new ColladaModelData(triangles);
		ColladaModelAdapter adapter = new ColladaModelAdapter(data);
		
	}
}
